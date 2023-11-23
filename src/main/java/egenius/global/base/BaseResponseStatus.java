package egenius.global.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    /**
     * 900: 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "Internal server error"),

    /**
     * 요청 실패
     */
    // Token, Code
    TokenExpiredException(HttpStatus.UNAUTHORIZED ,false, 2001, "토큰이 만료되었습니다."),
    TokenInvalidException(HttpStatus.UNAUTHORIZED , false, 2002, "토큰이 유효하지 않습니다."),
    TokenNullException(HttpStatus.UNAUTHORIZED , false, 2003, "토큰이 존재하지 않습니다."),
    JWT_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR , false, 2004, "토큰 생성에 실패했습니다."),
    JWT_VALID_FAILED(HttpStatus.UNAUTHORIZED , false, 2005, "토큰 검증에 실패했습니다."),
    EXPIRED_AUTH_CODE(HttpStatus.UNAUTHORIZED , false, 2006, "인증번호가 만료되었습니다."),
    WRONG_AUTH_CODE(HttpStatus.UNAUTHORIZED , false, 2007, "인증번호가 일치하지 않습니다."),

    // User
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, false, 2100, "사용중인 이메일입니다."),
    DUPLICATED_USER(HttpStatus.CONFLICT, false, 2101, "이미 가입된 유저입니다."),
    MASSAGE_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 2102, "인증번호 전송에 실패했습니다."),
    MASSAGE_VALID_FAILED(HttpStatus.UNAUTHORIZED, false, 2103, "인증번호가 일치하지 않습니다."),
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED , false, 2104, "없는 아이디거나 비밀번호가 틀렸습니다."),
    WITHDRAWAL_USER(HttpStatus.FORBIDDEN, false, 2105, "탈퇴한 회원입니다."),
    NO_EXIST_USER(HttpStatus.NOT_FOUND, false, 2106, "존재하지 않는 유저 정보입니다."),
    USER_STATUS_IS_NOT_FOUND(HttpStatus.NOT_FOUND, false, 2107, "존재하지 않는 유저 상태입니다."),
    PASSWORD_SAME_FAILED(HttpStatus.BAD_REQUEST, false, 2108, "현재 사용중인 비밀번호 입니다."),
    PASSWORD_CONTAIN_NUM_FAILED(HttpStatus.BAD_REQUEST, false, 2109, "휴대폰 번호를 포함한 비밀번호 입니다."),
    PASSWORD_CONTAIN_EMAIL_FAILED(HttpStatus.BAD_REQUEST, false, 2110, "이메일이 포함된 비밀번호 입니다."),

    // Dog
    NO_EXIST_DOG_BREED(HttpStatus.NOT_FOUND, false, 2200, "존재하지 않는 품종입니다."),
    NO_EXIST_DOG(HttpStatus.NOT_FOUND, false, 2201, "존재하지 않는 반려견입니다."),

    // Address
    NO_EXIST_ADDRESS(HttpStatus.NOT_FOUND, false, 2300, "존재하지 않는 주소입니다.");


    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
