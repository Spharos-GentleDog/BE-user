package egenius.address.presentation;

import egenius.global.base.BaseResponse;
import egenius.address.dto.AddressRegistrationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/address")
@Slf4j
@RequiredArgsConstructor
public class AddressController {

    @Operation(summary = "배송지 등록", description = "배송지 등록", tags = { "User Address" })
    @PostMapping("")
    public BaseResponse<?> addressRegister(@RequestBody AddressRegistrationRequestDto addressRegistrationRequestDto) {

        return new BaseResponse<>();
    }
}
