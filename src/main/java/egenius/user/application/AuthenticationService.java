package egenius.user.application;

import egenius.user.dto.SignInRequestDto;
import egenius.user.dto.SignUpRequestDto;
import egenius.user.response.SignInResponse;

public interface AuthenticationService {

    void signUp(SignUpRequestDto signUpRequestDto);
    SignInResponse signIn(SignInRequestDto signInRequestDto);
}
