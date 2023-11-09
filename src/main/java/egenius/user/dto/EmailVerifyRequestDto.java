package egenius.user.dto;

import lombok.Getter;

@Getter
public class EmailVerifyRequestDto {

    private String userEmail;
    private String code;

}
