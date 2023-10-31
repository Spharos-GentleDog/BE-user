package egenius.user.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String userEmail;
    private String password;
    private String name;
    private String phoneNumber;
    private String userAge;
    private String userGender;

}
