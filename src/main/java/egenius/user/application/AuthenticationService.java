package egenius.user.application;

import egenius.global.exception.BaseException;
import egenius.user.dto.SignInRequestDto;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.response.SignInResponse;
import egenius.user.response.SignUpResponse;

public interface AuthenticationService {

    SignUpResponse signUp(SignUpRequestDto signUpRequestDto) throws BaseException;
    SignInResponse signIn(SignInRequestDto signInRequestDto) throws BaseException;
}
