package egenius.user.application;

import egenius.global.base.BaseResponseStatus;
import egenius.global.exception.BaseException;
import egenius.global.config.security.JwtTokenProvider;
import egenius.global.util.RedisUtil;
import egenius.user.dto.SignInRequestDto;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.entity.User;
import egenius.user.infrastructure.UserRepository;
import egenius.user.response.SignInResponse;
import egenius.user.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImple implements AuthenticationService{

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final RedisUtil redisUtil;

    private final RedisTemplate redisTemplate;
    @Value("${JWT.token.refresh-expiration-time}")
    private long refreshExpirationTime;

    /**
     * 1. 시큐리티 회원가입
     * 2. 시큐리티 로그인
     * 3. 리프레쉬 토큰 재발급
     * 4. 로그아웃
     */

    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = modelMapper.map(signUpRequestDto, User.class);

        user.hashPassword(user.getPassword());
        userRepository.save(user);

        SignUpResponse.builder()
                .userEmail(user.getUserEmail())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public SignInResponse signIn(SignInRequestDto signInRequestDto) {

        // 로그인 하는 이메일이 없다면 에러
        User user = userRepository.findByUserEmail(signInRequestDto.getUserEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN));

        // 탈퇴한 회원이면 로그인 불가
        if (user.getDeletedAt() != null) {
            throw new BaseException(BaseResponseStatus.WITHDRAWAL_USER);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getUserEmail(),
                        signInRequestDto.getPassword()
                )
        );

        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        log.info("accessToken is : {}" , accessToken);
        log.info("refreshToken is : {}" , refreshToken);

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userEmail(user.getUserEmail())
                .userName(user.getUsername())
                .build();
    }

//    public SignInResponse refreshToken(String refreshToken) throws BaseException {
//
//        final String loginId;
//        loginId = jwtTokenProvider.getLoginId(refreshToken);
//
//        User user = this.userRepository.findByLoginId(loginId)
//                .orElseThrow();
//        // refresh 토큰 검증해서 만료 안되었으면
//
//        String redisInRefreshToken = (String) redisTemplate.opsForValue().get(loginId); //레디스에서 key loginId로 value refreshToken 가져온다
//        if(!redisInRefreshToken.equals(refreshToken)){                                  //내가 가진 refreshtoken이랑 레디스 refreshtoken 다르면 예외
//            throw new BaseException(TokenInvalidException);                             // Redis에서 해당 유저 loginId key 를 삭제하고 재로그인 하도록 클라이언트를 리턴한다
//        } else {
//            redisUtil.deleteData(loginId);
//        }
//
//        //내가 가진 refreshtoken이랑 레디스 refreshtoken같으면 레디스 수정
//        String newAccessToken = jwtTokenProvider.generateToken(user);
//        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);
//
//        redisTemplate.opsForValue().set(
//                loginId,
//                newRefreshToken,
//                refreshExpirationTime,
//                TimeUnit.MILLISECONDS
//        );
//
//        return SignInResponse.builder()
//                .accessToken(newAccessToken)
//                .refreshToken(newRefreshToken)
//                .loginId(loginId)
//                .build();
//
//
//    }
//
//
//    // 로그아웃
//    @Transactional
//    public void logout(String email) throws BaseException {
//        //Token에서 로그인한 사용자 정보 get해 로그아웃 처리
//        String loginId = jwtTokenProvider.getLoginId(email);
//        log.info("u is : {}" , loginId);
//        redisTemplate.delete(loginId); //Token 삭제
//
//    }
}
