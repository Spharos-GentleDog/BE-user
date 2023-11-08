package egenius.user.presentation;

import egenius.global.base.BaseResponse;
import egenius.user.application.MailService;
import egenius.user.dto.EmailRequestDto;
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

    @Operation(summary = "메일인증 요청", description = "메일인증 요청", tags = { "User Sign" })
    @GetMapping("/signup/email-auth")
    public BaseResponse<?> sendEmailAuthentication(@RequestParam("email") String email) throws MessagingException {
        mailService.sendEmailAuthentication(email);
        return new BaseResponse<>();
    }

    @Operation(summary = "메일인증 확인", description = "메일인증", tags = { "User Sign" })
    @PostMapping("/signup/email-verify")
    public BaseResponse<?> emailVerify(@RequestBody EmailRequestDto emailRequestDto) {
        mailService.verifyEmailCode(emailRequestDto.getEmail(), emailRequestDto.getCode());
        return new BaseResponse<>();
    }
}
