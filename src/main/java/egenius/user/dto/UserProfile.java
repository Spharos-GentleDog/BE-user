//package egenius.user.dto;
//
//import egenius.user.entity.User;
//import lombok.Getter;
//
//@Getter
//public class UserProfile {
//
//    private final Long oauthId;
//    private final String usersName;
//    private final String userEmail;
//
//    public UserProfile(Long oauthId, String usersName, String userEmail) {
//        this.oauthId = oauthId;
//        this.usersName = usersName;
//        this.userEmail = userEmail;
//    }
//
//    public User toUser() {
//        return new User(oauthId, usersName, userEmail);
//    }
//}