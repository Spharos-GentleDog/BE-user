package egenius.user.dto;

import lombok.Getter;

@Getter
public class AddressRegistrationRequestDto {

    private String userAddress;
    private String addressName;
    private Integer recipientPhoneNumber;
    private String recipientName;
    private String addressRequestMessage;
    private String entrancePassword;

}
