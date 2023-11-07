package egenius.address.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class AddressInfoResponse {

        private Long addressId;
        private String userAddress;
        private String addressName;
        private String recipientPhoneNumber;
        private String recipientName;
        private String addressRequestMessage;
        private String entrancePassword;
        private Boolean defaultAddress;

}
