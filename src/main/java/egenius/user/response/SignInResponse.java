package egenius.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    private String accessToken;
    private String refreshToken;
    private String userEmail;
    private String usersName;

}
