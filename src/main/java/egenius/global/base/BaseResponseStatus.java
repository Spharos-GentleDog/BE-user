package egenius.global.base;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(true, 200, "요청에 성공하였습니다."),


    /**
     * 요청 실패
     */
    // Token
    TokenExpiredException(false, 2001, "토큰이 만료되었습니다."),
    TokenInvalidException(false, 2002, "토큰이 유효하지 않습니다."),
    TokenNullException(false, 2003, "토큰이 존재하지 않습니다."),
    JWT_CREATE_FAILED(false, 2004, "토큰 생성에 실패했습니다."),

    // User
    POST_EXISTS_LOGIN_ID(false, 2101, "중복된 아이디입니다."),
    MASSAGE_SEND_FAILED(false, 2102, "인증번호 전송에 실패했습니다."),
    MASSAGE_VALID_FAILED(false, 2103, "인증번호가 일치하지 않습니다."),
    FAILED_TO_LOGIN(false, 2104, "없는 아이디거나 비밀번호가 틀렸습니다."),
    NO_LOOKUP_VALUE(false, 2105, "조회된 데이터가 없습니다."),
    WITHDRAWAL_USER(false, 2106, "탈퇴한 회원입니다.");

//    NO_EXIST_USER(false, 2104, "존재하지 않는 유저 정보입니다."),
//    FAILED_TO_CARD_NUMBER(false, 2106, "바코드 생성에 실패했습니다"),
//    USER_RETRIEVE_FAILED(false, 2107, "회원정보 조회에 실패했습니다."),
//    USER_UPDATE_FAILED(false, 2108, "회원정보 변경에 실패했습니다."),
//    PASSWORD_RETRIEVE_FAILED(false, 2109, "비밀번호 조회에 실패했습니다."),
//    PASSWORD_UPDATE_FAILED(false, 2110, "비밀번호 변경에 실패했습니다."),
//    PASSWORD_CONTAIN_NUM_FAILED(false, 2111,"휴대폰 번호를 포함한 비밀번호 입니다."),
//    PASSWORD_SAME_FAILED(false, 2112,"현재 사용중인 비밀번호 입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
