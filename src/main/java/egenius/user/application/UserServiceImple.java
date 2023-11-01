package egenius.user.application;


import egenius.global.base.BaseResponseStatus;
import egenius.global.exception.BaseException;
import egenius.user.entity.User;
import egenius.user.infrastructure.UserRepository;
import egenius.user.response.UserInfoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImple implements UserService{

    private final UserRepository userRepository;
    @Override
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
}
