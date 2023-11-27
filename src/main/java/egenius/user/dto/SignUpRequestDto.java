package egenius.user.dto;

import egenius.user.entity.UserGenderStatus;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String userEmail;
    private String password;
    private String usersName;
    private String userPhoneNumber;
    private Integer userAge;
    private UserGenderStatus userGender;

}
