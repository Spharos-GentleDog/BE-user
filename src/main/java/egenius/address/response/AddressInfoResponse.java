package egenius.address.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoResponse {

        private Long id;
        private String userAddress;
        private String addressName;
        private String recipientPhoneNumber;
        private String recipientName;
        private String addressRequestMessage;
        private String entrancePassword;
        private Boolean defaultAddress;

}
