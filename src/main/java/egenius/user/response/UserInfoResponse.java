package egenius.user.response;

import egenius.user.entity.UserGenderStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private String userEmail;
    private String usersName;
    private String userPhoneNumber;
    private Integer userAge;
    private UserGenderStatus userGender;

}
