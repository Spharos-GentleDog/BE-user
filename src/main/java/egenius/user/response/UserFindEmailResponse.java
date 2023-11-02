package egenius.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserFindEmailResponse {

    private String userEmail;
}
