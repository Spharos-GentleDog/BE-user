package egenius.user.presentation;


import egenius.global.base.BaseResponse;
import egenius.user.application.AuthenticationService;
import egenius.user.dto.SignInRequestDto;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.response.SignInResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "회원가입", description = "회원가입", tags = { "User Sign" })
    @PostMapping("/signup")
    public BaseResponse<?> signUp(
            @RequestBody SignUpRequestDto signUpRequestDto) {
            authenticationService.signUp(signUpRequestDto);
            return new BaseResponse<>();
    }

    @Operation(summary = "로그인", description = "로그인", tags = { "User Sign" })
    @PostMapping("/signin")
    public BaseResponse<SignInResponse> signIn(
            @RequestBody SignInRequestDto signinRequestDto) {
            SignInResponse signInResponse = authenticationService.signIn(signinRequestDto);
            return new BaseResponse<>(signInResponse);
    }

}
