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
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/address")
@Slf4j
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * 1. 배송지 등록
     * 2. 배송지 조회
     * 3. 배송지 수정
     * 4. 대표 배송지 변경
     * 5. 배송지 삭제
     */

    @Operation(summary = "배송지 등록", description = "배송지 등록", tags = { "User Address" })
    @PostMapping("")
    public BaseResponse<?> addressRegister(Principal principal,
                                           @RequestBody AddressRegistrationRequestDto addressRegistrationRequestDto) {
        addressService.registerAddress(principal.getName(), addressRegistrationRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "배송지 조회", description = "배송지 조회", tags = { "User Address" })
    @GetMapping("")
    public BaseResponse<List<AddressInfoResponse>> addressFind(Principal principal) {
        List<AddressInfoResponse> addressInfoResponse = addressService.findAddress(principal.getName());
        return new BaseResponse<>(addressInfoResponse);
    }

    @Operation(summary = "배송지 수정", description = "배송지 수정", tags = { "User Address" })
    @PutMapping("")
    public BaseResponse<?> addressUpdate(@RequestParam("addressId") Long addressId,
                                         @RequestBody AddressRegistrationRequestDto addressRegistrationRequestDto) {
        addressService.updateAddress(addressId, addressRegistrationRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "대표 배송지 변경", description = "대표 배송지 변경", tags = { "User Address" })
    @PutMapping("/default")
    public BaseResponse<?> addressUpdateDefault(Principal principal,
                                                @RequestParam("oldAddressId") Long oldAddressId,
                                                @RequestParam("newAddressId") Long newAddressId) {
        addressService.updateDefaultAddress(principal.getName(), oldAddressId, newAddressId);
        return new BaseResponse<>();
    }

    @Operation(summary = "배송지 삭제", description = "배송지 삭제", tags = { "User Address" })
    @DeleteMapping("")
    public BaseResponse<?> addressDelete(Principal principal,
                                         @RequestParam("addressId") Long addressId) {
        addressService.deleteAddress(principal.getName(), addressId);
        return new BaseResponse<>();
    }





}
