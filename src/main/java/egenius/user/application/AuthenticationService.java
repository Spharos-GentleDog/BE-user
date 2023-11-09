package egenius.user.application;

import egenius.user.dto.SignUpRequestDto;
import egenius.user.dto.SignInRequestDto;
import egenius.user.response.SignInResponse;

public interface AuthenticationService {

    void signUp(SignUpRequestDto signUpRequestDto);
    SignInResponse signIn(SignInRequestDto signInRequestDto);
    void signOut(String accessToken);
    SignInResponse regenerateToken(String token, String email);
}
