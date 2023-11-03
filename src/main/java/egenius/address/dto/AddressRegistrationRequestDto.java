package egenius.address.dto;

import lombok.Getter;

@Getter
public class AddressRegistrationRequestDto {

    private String userAddress;
    private String addressName;
    private String recipientPhoneNumber;
    private String recipientName;
    private String addressRequestMessage;
    private String entrancePassword;

}
