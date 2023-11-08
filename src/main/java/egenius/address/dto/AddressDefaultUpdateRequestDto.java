package egenius.address.dto;

import lombok.Getter;

@Getter
public class AddressDefaultUpdateRequestDto {

    private Long oldDefaultAddressId;
    private Long newDefaultAddressId;

}
