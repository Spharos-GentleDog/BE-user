package egenius.user.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String userEmail;
    private String password;
    private String userName;
    private String userPhoneNumber;
    private String userAge;
    private String userGender;

}
