package egenius.address.entity;

import egenius.address.dto.AddressRegistrationRequestDto;
import egenius.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_address", length = 100)
    private String userAddress;

    @Column(name = "address_name", length = 20, nullable = false)
    private String addressName;

    @Column(name = "recipient_phone_number", length = 15, nullable = false)
    private String recipientPhoneNumber;

    @Column(name = "recipient_name", length = 20, nullable = false)
    private String recipientName;

    @Column(name = "address_request_message", length = 100)
    private String addressRequestMessage;

    @Column(name = "entrance_password", length = 20)
    private String entrancePassword;


    // 1. 주소 정보 수정
    public void updateAddress(AddressRegistrationRequestDto addressRegistrationRequestDto) {
        this.userAddress = addressRegistrationRequestDto.getUserAddress();
        this.addressName = addressRegistrationRequestDto.getAddressName();
        this.recipientPhoneNumber = addressRegistrationRequestDto.getRecipientPhoneNumber();
        this.recipientName = addressRegistrationRequestDto.getRecipientName();
        this.addressRequestMessage = addressRegistrationRequestDto.getAddressRequestMessage();
        this.entrancePassword = addressRegistrationRequestDto.getEntrancePassword();

    }
}