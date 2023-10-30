package egenius.user.presentation;

import egenius.global.base.BaseResponse;
import egenius.user.dto.DogRegistrationRequestDto;
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
public class DogController {

    @Operation(summary = "반려견 등록", description = "반려견 등록", tags = { "Pet Dog" })
    @PostMapping("/petdog")
    public BaseResponse<?> dogRegister(@RequestBody DogRegistrationRequestDto dogRegisterRequestDto) {

        return new BaseResponse<>();
    }

}
