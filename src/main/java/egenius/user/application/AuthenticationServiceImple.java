package egenius.user.application;

import egenius.address.entity.Address;
import egenius.address.infrastructure.AddressRepository;
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

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImple implements AuthenticationService{

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
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
        log.info("user is : {}" , user);
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
        log.info("signInRequestDto is : {}" , signInRequestDto.getPassword());
        log.info("user is : {}" , user.getPassword());

        // 회원정보 일치하지 않으면 에러
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
                .usersName(user.getUsersName())
                .build();
    }

    public SignInResponse regenerateToken(String token, String email) {


        String refreshToken = token.substring(7);

        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        log.info("refreshToken is : {}" , refreshToken);

        log.info("user : {}" , user);
        log.info("user is : {}" , user.getUsersName());

        // key userEmain로 value refreshToken 가져온다
        String redisInRefreshToken = (String) redisTemplate.opsForValue().get(email);
        // redis에 저장된 refreshToken과 일치하는지 확인한다
        if (redisInRefreshToken == null) {
            // redis에 저장된 refreshToken이 없다면 만료된 토큰이거나 잘못된 토큰이다
            throw new BaseException(BaseResponseStatus.TokenExpiredException);
        } else if(!redisInRefreshToken.equals(refreshToken)) {
            // redis에 저장된 refreshToken과 일치하지 않는다면 잘못된 토큰이다
            throw new BaseException(BaseResponseStatus.TokenInvalidException);
        } else {
            // Redis에서 해당 유저 loginId key 를 삭제하고 재로그인 하도록 클라이언트를 리턴한다
            redisUtil.deleteData(email);
        }



        //내가 가진 refreshtoken이랑 레디스 refreshtoken같으면 레디스 수정
        String newAccessToken = jwtTokenProvider.generateToken(user);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);

        redisTemplate.opsForValue().set(
                email,
                newRefreshToken,
                refreshExpirationTime,
                TimeUnit.MILLISECONDS
        );



        return SignInResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .userEmail(email)
                .usersName(user.getUsersName())
                .build();

    }

    // 로그아웃
    public void signOut(String email) {
        log.info("email is : {}" , email);
        redisTemplate.delete(email); //Token 삭제

    }
}
