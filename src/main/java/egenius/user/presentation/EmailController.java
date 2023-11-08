package egenius.user.presentation;

import egenius.global.base.BaseResponse;
import egenius.user.application.MailService;
import egenius.user.dto.EmailAuthRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class EmailController {

    private final MailService mailService;

    @Operation(summary = "이메일 중복 검사", description = "이메일 중복 검사", tags = { "User Sign" })
    @GetMapping("/signup/email-check")
    public BaseResponse<?> checkEmail(@RequestHeader("email") String userEmail) {
        return new BaseResponse<>(mailService.verifyEmail(userEmail));
    }


    @Operation(summary = "이메일 인증 요청", description = "이메일 인증 요청", tags = { "User Sign" })
    @PostMapping("/signup/email-auth")
    public BaseResponse<?> sendEmailAuthentication(@RequestHeader("email") String userEmail)
            throws MessagingException {
        mailService.sendEmailAuthentication(userEmail);
        return new BaseResponse<>();
    }

    @Operation(summary = "이메일 인증 확인", description = "이메일 인증 확인", tags = { "User Sign" })
    @GetMapping ("/signup/email-verify")
    public BaseResponse<?> emailVerify(@RequestBody EmailAuthRequestDto emailAuthRequestDto) {
        mailService.verifyEmailCode(emailAuthRequestDto.getEmail(), emailAuthRequestDto.getCode());
        return new BaseResponse<>();
    }
}
