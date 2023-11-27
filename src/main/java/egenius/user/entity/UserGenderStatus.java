package egenius.user.entity;

import egenius.global.Enums.BaseEnum;
import egenius.global.Enums.BaseEnumConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // BaseEnum의 method를 impl하는것을 -> Getter로 대체
@AllArgsConstructor // 생성자를 직접 만들지 않고 -> AllArgs로 대체
public enum UserGenderStatus implements BaseEnum<Integer, String> {
    /**
     * 1. 코드 작성
     * 2. field 선언
     * 3. converter 구현
     */

    NONE(0,"선택안함"),
    MAN(1,"남자"),
    FEMALE(2,"여자");

    // 2. field 선언
    private final Integer code;
    private final String description;


    // 3. converter 구현
    @Converter(autoApply = true)
    static class thisConverter extends BaseEnumConverter<UserGenderStatus, Integer, String> {
        public thisConverter() {
            super(UserGenderStatus.class);
        }
    }
}
