package egenius.user.application;


import egenius.global.base.BaseException;
import egenius.global.config.security.JwtTokenProvider;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.entity.User;
import egenius.user.infrastructure.UserRepository;
import egenius.user.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImple implements AuthenticationService{

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    private final RedisTemplate redisTemplate;
    @Value("${JWT.token.refresh-expiration-time}")
    private long refreshExpirationTime;

    /**
     * 1. 시큐리티 회원가입
     */

    @Override
    public SignUpResponse signUp(SignUpRequestDto signUpRequestDto) throws BaseException {
        User user = modelMapper.map(signUpRequestDto, User.class);

        String jwtToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        user.hashPassword(user.getPassword());
        userRepository.save(user);

        return SignUpResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .loginId(signUpRequestDto.getLoginId())
                .build();
    }
}
