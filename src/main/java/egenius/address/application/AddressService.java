package egenius.address.application;

import egenius.address.dto.AddressRegistrationRequestDto;
import egenius.address.response.AddressInfoResponse;

public interface AddressService {

    void registerAddress(String userEmail, AddressRegistrationRequestDto addressRegistrationRequestDto);
    AddressInfoResponse findAddress(String userEmail);
    void updateAddress(String userEmail, AddressRegistrationRequestDto addressRegistrationRequestDto);
    void updateDefaultAddress(String userEmail, Long addressId);
    void deleteAddress(String userEmail, Long addressId);
}
