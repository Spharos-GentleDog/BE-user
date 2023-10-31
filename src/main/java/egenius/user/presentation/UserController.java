package egenius.user.presentation;


import egenius.global.base.BaseResponse;
import egenius.user.application.AuthenticationService;
import egenius.user.dto.SignInRequestDto;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.response.SignInResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    /**
     *
     * 1. 회원가입
     * 2. 로그인
     * 3. 유저 정보 조회
     * 4. 유저 정보 수정
     * 5. 유저 로그아웃
     * 6. 유저 아이디 찾기
     * 7. 유저 비밀번호 변경
     * 8. 유저 비밀번호 찾기
     * 9. 리프레쉬 토큰 재발급
     *
     * @param signUpRequestDto
     * @return
     */

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

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회", tags = { "User Sign" })
    @GetMapping("/info")
    public BaseResponse<?> getUserInfo() {


        return new BaseResponse<>();
    }


}
