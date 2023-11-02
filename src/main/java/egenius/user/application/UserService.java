package egenius.user.application;

import egenius.user.dto.UserInfoUpdateDto;
import egenius.user.response.UserFindEmailResponse;
import egenius.user.response.UserInfoResponse;

public interface UserService {

    UserInfoResponse getUserInfo(String email);
    void updateUserInfo(String email, UserInfoUpdateDto userInfoUpdateDto);
    UserFindEmailResponse findUserEmail(String userPhoneNumber);
    void updateUserPassword(String userEmail, String newPassword);
    void withdraw(String userEmail);

}
