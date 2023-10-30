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
    @PostMapping("/signup/emailauth/{email}")
    public BaseResponse<?> sendEmailPath(@PathVariable String email) throws MessagingException {
            mailService.sendEmail(email);
            return new BaseResponse<>();
    }

    @Operation(summary = "메일중복 검사", description = "메일 중복 검사", tags = { "User Sign" })
    @PostMapping("/signup/emailcheck/{email}")
    public BaseResponse<?> checkEmail(@PathVariable String email) {
        mailService.checkEmail(email);
        return new BaseResponse<>();
    }

    @Operation(summary = "메일인증", description = "메일인증", tags = { "User Sign" })
    @PostMapping("signup/authcode")
    public BaseResponse<?> sendEmailAndCode(@RequestBody EmailRequestDto emailRequestDto) {

        mailService.verifyEmailCode(emailRequestDto.getEmail(), emailRequestDto.getCode());
        return new BaseResponse<>();

    }
}
