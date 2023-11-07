package egenius.address.application;

import egenius.address.dto.AddressRegistrationRequestDto;
import egenius.address.response.AddressInfoResponse;

import java.util.List;

public interface AddressService {

    void registerAddress(String userEmail, AddressRegistrationRequestDto addressRegistrationRequestDto);
    List<AddressInfoResponse> findAddress(String userEmail);
    void updateAddress(Long addressId, AddressRegistrationRequestDto addressRegistrationRequestDto);
    void updateDefaultAddress(String userEmail, Long oldAddressId, Long newAddressId);
    void deleteAddress(String userEmail, Long addressId);
}
