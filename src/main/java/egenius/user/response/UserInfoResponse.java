package egenius.user.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private String userEmail;
    private String userName;
    private String userPhoneNumber;
    private Integer userAge;
    private Integer userGender;

}
