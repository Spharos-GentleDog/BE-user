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
    TokenExpiredException(HttpStatus.BAD_REQUEST,false, 2001, "토큰이 만료되었습니다."),
    TokenInvalidException(HttpStatus.BAD_REQUEST, false, 2002, "토큰이 유효하지 않습니다."),
    TokenNullException(HttpStatus.BAD_REQUEST, false, 2003, "토큰이 존재하지 않습니다."),
    JWT_CREATE_FAILED(HttpStatus.BAD_REQUEST, false, 2004, "토큰 생성에 실패했습니다."),
    JWT_VALID_FAILED(HttpStatus.BAD_REQUEST, false, 2005, "토큰 검증에 실패했습니다."),
    EXPIRED_AUTH_CODE(HttpStatus.BAD_REQUEST, false, 2006, "인증번호가 만료되었습니다."),
    WRONG_AUTH_CODE(HttpStatus.BAD_REQUEST, false, 2007, "인증번호가 일치하지 않습니다."),

    // User
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, false, 2100, "사용중인 이메일입니다."),
    MASSAGE_SEND_FAILED(HttpStatus.BAD_REQUEST, false, 2101, "인증번호 전송에 실패했습니다."),
    MASSAGE_VALID_FAILED(HttpStatus.BAD_REQUEST, false, 2102, "인증번호가 일치하지 않습니다."),
    FAILED_TO_LOGIN(HttpStatus.BAD_REQUEST, false, 2103, "없는 아이디거나 비밀번호가 틀렸습니다."),
    NO_LOOKUP_VALUE(HttpStatus.BAD_REQUEST, false, 2104, "조회된 데이터가 없습니다."),
    WITHDRAWAL_USER(HttpStatus.BAD_REQUEST, false, 2105, "탈퇴한 회원입니다.");

//    NO_EXIST_USER(false, 2104, "존재하지 않는 유저 정보입니다."),
//    FAILED_TO_CARD_NUMBER(false, 2106, "바코드 생성에 실패했습니다"),
//    USER_RETRIEVE_FAILED(false, 2107, "회원정보 조회에 실패했습니다."),
//    USER_UPDATE_FAILED(false, 2108, "회원정보 변경에 실패했습니다."),
//    PASSWORD_RETRIEVE_FAILED(false, 2109, "비밀번호 조회에 실패했습니다."),
//    PASSWORD_UPDATE_FAILED(false, 2110, "비밀번호 변경에 실패했습니다."),
//    PASSWORD_CONTAIN_NUM_FAILED(false, 2111,"휴대폰 번호를 포함한 비밀번호 입니다."),
//    PASSWORD_SAME_FAILED(false, 2112,"현재 사용중인 비밀번호 입니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private String message;
}
