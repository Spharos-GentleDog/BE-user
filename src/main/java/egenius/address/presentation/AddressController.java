package egenius.address.presentation;

import egenius.address.application.AddressService;
import egenius.address.response.AddressInfoResponse;
import egenius.global.base.BaseResponse;
import egenius.address.dto.AddressRegistrationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user/address")
@Slf4j
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "배송지 등록", description = "배송지 등록", tags = { "User Address" })
    @PostMapping("")
    public BaseResponse<?> addressRegister(Principal principal,
                                           @RequestBody AddressRegistrationRequestDto addressRegistrationRequestDto) {
        addressService.registerAddress(principal.getName(), addressRegistrationRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "배송지 조회", description = "배송지 조회", tags = { "User Address" })
    @PostMapping("")
    public BaseResponse<AddressInfoResponse> addressFind(Principal principal) {
        AddressInfoResponse addressInfoResponse = addressService.findAddress(principal.getName());
        return new BaseResponse<>(addressInfoResponse);
    }

    @Operation(summary = "배송지 수정", description = "배송지 수정", tags = { "User Address" })
    @PostMapping("")
    public BaseResponse<?> addressUpdate(Principal principal,
                                         @RequestBody AddressRegistrationRequestDto addressRegistrationRequestDto) {
        addressService.updateAddress(principal.getName(), addressRegistrationRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "대표 배송지 변경", description = "대표 배송지 변경", tags = { "User Address" })
    @PostMapping("/default")
    public BaseResponse<?> addressUpdateDefault(Principal principal,
                                                @RequestParam("addressId") Long addressId) {
        addressService.updateDefaultAddress(principal.getName(), addressId);
        return new BaseResponse<>();
    }

    @Operation(summary = "배송지 삭제", description = "배송지 삭제", tags = { "User Address" })
    @PostMapping("")
    public BaseResponse<?> addressDelete(Principal principal,
                                         @RequestParam("addressId") Long addressId) {
        addressService.deleteAddress(principal.getName(), addressId);
        return new BaseResponse<>();
    }





}
