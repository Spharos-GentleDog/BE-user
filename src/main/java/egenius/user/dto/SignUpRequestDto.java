package egenius.user.dto;

import lombok.*;

@ToString
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class SignUpRequestDto {

    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;

}
