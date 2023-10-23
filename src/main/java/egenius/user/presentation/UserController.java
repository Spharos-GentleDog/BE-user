package egenius.user.presentation;


import egenius.global.base.BaseException;
import egenius.global.base.BaseResponse;
import egenius.user.application.AuthenticationService;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.response.SignUpResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;


    @Operation(summary = "회원가입", description = "회원가입", tags = { "User Sign" })
    @PostMapping("/signup")
    public BaseResponse<SignUpResponse> signup(
            @RequestBody SignUpRequestDto signUpRequestDto
            ) {
        try {
            SignUpResponse signUpResponse = authenticationService.signUp(signUpRequestDto);
            return new BaseResponse<>(signUpResponse);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
