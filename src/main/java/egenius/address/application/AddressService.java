package egenius.address.application;

import egenius.address.dto.AddressDefaultUpdateRequestDto;
import egenius.address.dto.AddressRegistrationRequestDto;
import egenius.address.response.AddressInfoResponse;

import java.util.List;

public interface AddressService {

    void registerAddress(String userEmail, AddressRegistrationRequestDto addressRegistrationRequestDto);
    List<AddressInfoResponse> findAddress(String userEmail);
    AddressInfoResponse findDefaultAddress(String userEmail);
    void updateAddress(Long addressId, AddressRegistrationRequestDto addressRegistrationRequestDto);
    void updateDefaultAddress(String userEmail, AddressDefaultUpdateRequestDto addressDefaultUpdateRequestDto);
    void deleteAddress(Long addressId);
}
