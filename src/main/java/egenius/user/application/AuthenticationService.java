package egenius.user.application;

import egenius.global.base.BaseException;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.response.SignUpResponse;

public interface AuthenticationService {

    SignUpResponse signUp(SignUpRequestDto signUpRequestDto) throws BaseException;
}
