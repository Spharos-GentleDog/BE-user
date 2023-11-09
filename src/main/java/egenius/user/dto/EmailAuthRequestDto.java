package egenius.user.dto;

import lombok.Getter;

@Getter
public class EmailAuthRequestDto {

    private String email;
    private String code;

}
