package egenius.user.presentation;

import egenius.global.base.BaseResponse;
import egenius.user.application.AuthenticationService;
import egenius.user.application.UserService;
import egenius.user.dto.SignInRequestDto;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.dto.UserInfoUpdateDto;
import egenius.user.dto.UserPasswordUpdateDto;
import egenius.user.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.PUT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    /**
     *
     * 1. 회원가입
     * 2. 로그인
     * 3. 유저 정보 조회
     * 4. 유저 정보 수정
     * 5. 유저 이메일 찾기
     * 6. 유저 비밀번호 수정
     * 7. 유저 탈톼
     * 8. 유저 로그아웃
     * 9. 리프레쉬 토큰 재발급
     *
     */

    @Operation(summary = "회원가입", description = "회원가입", tags = { "User Sign" })
    @PostMapping("/signup")
    public BaseResponse<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
            authenticationService.signUp(signUpRequestDto);
            return new BaseResponse<>();
    }

    @Operation(summary = "로그인", description = "로그인", tags = { "User Sign" })
    @PostMapping("/signin")
    public BaseResponse<SignInResponse> signIn(@RequestBody SignInRequestDto signinRequestDto) {
            SignInResponse signInResponse = authenticationService.signIn(signinRequestDto);
            return new BaseResponse<>(signInResponse);
    }

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회", tags = { "User Sign" })
    @GetMapping("/info")
    public BaseResponse<UserInfoResponse> getUserInfo(@RequestHeader("email") String userEmail) {
        // 토큰값에서 loginId 추출
        UserInfoResponse userInfoResponse = userService.getUserInfo(userEmail);
        return new BaseResponse<>(userInfoResponse);
    }

    @Operation(summary = "유저 정보 수정", description = "유저 정보 수정", tags = { "User Sign" })
    @PutMapping("/info")
    public BaseResponse<?> updateUserInfo(@RequestHeader("email") String userEmail,
                                          @RequestBody UserInfoUpdateDto userInfoUpdateRequest) {

        userService.updateUserInfo(userEmail, userInfoUpdateRequest);
        return new BaseResponse<>();
    }

    @Operation(summary = "유저 이메일 찾기", description = "유저 이메일 찾기", tags = { "User Sign" })
    @GetMapping("/email")
    public BaseResponse<UserFindEmailResponse> findUserEmail(@RequestParam("phonenumber") String userPhoneNumber) {
        UserFindEmailResponse userFindEmailResponse = userService.findUserEmail(userPhoneNumber);
        return new BaseResponse<>(userFindEmailResponse);
    }

    @Operation(summary = "유저 비밀번호 수정", description = "유저 비밀번호 수정", tags = { "User Sign" })
    @PutMapping("/password")
    public BaseResponse<?> updateUserPassword(@RequestHeader("email") String userEmail,
                                        @RequestBody UserPasswordUpdateDto userPasswordUpdateDto) {
        userService.updateUserPassword(userEmail, userPasswordUpdateDto.getPassword());
        return new BaseResponse<>();
    }

    @Operation(summary = "유저 탈퇴", description = "유저 탈퇴", tags = { "User Sign" })
    @PutMapping("/withdraw")
    public BaseResponse<?> withdraw(@RequestHeader("email") String userEmail) {
        userService.withdraw(userEmail);
        return new BaseResponse<>();
    }

    @Operation(summary = "유저 로그아웃", description = "유저 로그아웃", tags = { "User Sign" })
    @PostMapping("/signout")
    public BaseResponse<?> signOut(@RequestHeader("email") String userEmail) {
        authenticationService.signOut(userEmail);
        return new BaseResponse<>();
    }

    @Operation(summary = "토큰 재발급", description = "access token이 만료 됐다면 실행", tags = { "User Sign" })
    @PostMapping("/refresh")
    public BaseResponse<SignInResponse> regenerateToken(HttpServletRequest request,
                                                        Principal principal) {

        String token = request.getHeader("Authorization");
        SignInResponse signInResponse = authenticationService.regenerateToken(token, principal.getName());
        return new BaseResponse<>(signInResponse);

    }

}
