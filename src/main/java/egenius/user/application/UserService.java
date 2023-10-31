package egenius.user.application;

import egenius.user.response.UserInfoResponse;

public interface UserService {

    UserInfoResponse getUserInfo(String email);

}
