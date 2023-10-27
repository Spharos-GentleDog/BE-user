package egenius.user.presentation;

import egenius.global.exception.BaseException;
import egenius.global.base.BaseResponse;
import egenius.user.application.MailService;
import egenius.user.dto.EmailRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static egenius.global.base.BaseResponseStatus.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class EmailController {

    private final MailService mailService;

    @Operation(summary = "메일인증 요청", description = "메일인증 요청", tags = { "User Sign" })
    @PostMapping("/signup/emailauth/{email}")
    public BaseResponse<String> sendEmailPath(@PathVariable String email) {
        try {
            mailService.sendEmail(email);
            return new BaseResponse<>("");
        } catch (MessagingException e) {
            return new BaseResponse<>(MASSAGE_SEND_FAILED);
        }
    }

    @Operation(summary = "메일인증", description = "메일인증", tags = { "User Sign" })
    @PostMapping("signup/authcode")
    public BaseResponse<String> sendEmailAndCode(@RequestBody EmailRequestDto emailRequestDto) {

        if (mailService.verifyEmailCode(emailRequestDto.getEmail(), emailRequestDto.getCode())) {
            return new BaseResponse<>("인증 성공");
        } else {
            return new BaseResponse<>(MASSAGE_VALID_FAILED);
        }
    }
}
