//package egenius.user.entity;
//
//import egenius.user.dto.UserProfile;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.function.Function;
//
//public enum OAuthAttributes {
//
//    GOOGLE("google", attributes -> new UserProfile(
//            (Long) attributes.get("sub"),
//            (String) attributes.get("name"),
//            (String) attributes.get("email")
//    )),
//
//    NAVER("naver", attributes -> {
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//        return new UserProfile(
//                (Long) response.get("id"),
//                (String) response.get("name"),
//                (String) response.get("email")
//        );
//    }),
//
//    KAKAO("kakao", attributes -> {
//        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
//        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
//        return new UserProfile(
//                (Long) kakaoAccount.get("id"),
//                (String) profile.get("nickname"),
//                (String) kakaoAccount.get("email")
//        );
//    });
//
//    private final String registrationId;
//    private final Function<Map<String, Object>, UserProfile> userProfileFactory;
//
//    OAuthAttributes(String registrationId,
//                    Function<Map<String, Object>, UserProfile> userProfileFactory) {
//        this.registrationId = registrationId;
//        this.userProfileFactory = userProfileFactory;
//    }
//
//    public static UserProfile extract(String registrationId, Map<String, Object> attributes) {
//        return Arrays.stream(values())
//                .filter(provider -> registrationId.equals(provider.registrationId))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new)
//                .userProfileFactory.apply(attributes);
//    }
//}
