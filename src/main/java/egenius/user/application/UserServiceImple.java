package egenius.user.application;


import egenius.global.base.BaseResponseStatus;
import egenius.user.entity.User;
import egenius.user.infrastructure.UserRepository;
import egenius.global.exception.BaseException;
import egenius.user.dto.UserInfoUpdateDto;
import egenius.user.response.UserFindEmailResponse;
import egenius.user.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImple implements UserService{

    /**
     *
     * 1. 유저 정보 조회
     * 2. 유저 정보 수정
     * 3. 유저 아이디 찾기
     * 4. 유저 비밀번호 수정
     * 5. 유저 탈퇴
     *
     * @param
     * @return
     */

    private final UserRepository userRepository;

    // 1. 유저 정보 조회
    @Override
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(String email) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        log.info("user is : {}", user);
        return UserInfoResponse.builder()
                .userEmail(user.getUserEmail())
                .usersName(user.getUsersName())
                .userPhoneNumber(user.getUserPhoneNumber())
                .userAge(user.getUserAge())
                .userGender(user.getUserGender())
                .build();
    }

    // 2. 유저 정보 수정
    @Override
    public void updateUserInfo(String email, UserInfoUpdateDto userInfoUpdateDto) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        user.updateUserInfo(userInfoUpdateDto.getUserEmail()
                ,userInfoUpdateDto.getUsersName()
                ,userInfoUpdateDto.getUserPhoneNumber()
                ,userInfoUpdateDto.getUserAge()
                ,userInfoUpdateDto.getUserGender());

    }

    // 3. 유저 이메일 찾기
    @Override
    @Transactional(readOnly = true)
    public UserFindEmailResponse findUserEmail(String userPhoneNumber) {
        User user = userRepository.findByUserPhoneNumber(userPhoneNumber)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        return UserFindEmailResponse.builder()
                .userEmail(user.getUserEmail())
                .build();

    }

    // 4. 유저 비밀번호 수정
    @Override
    public void updateUserPassword(String email, String newPassword) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        String phoneNum = user.getUserPhoneNumber();

        /**
         * 개인정보를 기반으로 구성된 비밀번호는 보안상의 위험성을 가지고 있습니다.
         * 예측 가능성, 해킹 공격에 취약 등등 많은 이유가 있으며
         * 많은 보안 정책과 규정은 사용자가 개인정보를 기반으로 한 비밀번호를 사용하지 않도록 권고하고 있습니다.
         */

        // 전화번호의 중간 4자리와 끝 4자리를 추출하여 비밀번호와 비교
        String middleNum = phoneNum.substring(3, 7);
        String lastNum = phoneNum.substring(phoneNum.length() - 4);

        // 비밀번호가 같거나, 비밀번호에 이메일이 포함되어 있거나, 비밀번호에 전화번호가 포함되어 있으면 실패
        if (new BCryptPasswordEncoder().matches(newPassword, user.getPassword())) {
            throw new BaseException(BaseResponseStatus.PASSWORD_SAME_FAILED);
        } else if (newPassword.contains(email)) {
            throw new BaseException(BaseResponseStatus.PASSWORD_UPDATE_FAILED);
        } else if (newPassword.contains(middleNum) || newPassword.contains(lastNum)) {
            throw new BaseException(BaseResponseStatus.PASSWORD_CONTAIN_NUM_FAILED);
        } else {
            // 비밀번호가 같지 않으면 비밀번호를 암호화하여 저장
            user.hashPassword(newPassword);
        }
    }

    // 5. 유저 탈퇴
    @Override
    public void withdraw(String email) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        user.deactivateUser();
    }



}
