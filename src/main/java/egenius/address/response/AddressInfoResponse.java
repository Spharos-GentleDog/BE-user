package egenius.address.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressInfoResponse {

        private String userAddress;
        private String addressName;
        private String recipientPhoneNumber;
        private String recipientName;
        private String addressRequestMessage;
        private String entrancePassword;

}
