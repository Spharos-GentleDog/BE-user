package egenius.user.dto;

import lombok.*;

@Getter
public class SignUpRequestDto {

    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;

}
