//package egenius.user.application;
//
//import egenius.global.base.BaseResponseStatus;
//import egenius.global.exception.BaseException;
//import egenius.user.dto.UserProfile;
//import egenius.user.entity.OAuthAttributes;
//import egenius.user.entity.User;
//import egenius.user.infrastructure.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        // OAuth 서비스(github, google, naver)에서 가져온 유저 정보를 담고있음
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        // OAuth 서비스 이름(ex. github, naver, google)
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        // OAuth 로그인 시 키 값. 구글, 네이버, 카카오 등 각 다르기 때문에 변수로 받아서 넣음
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
//                .getUserInfoEndpoint().getUserNameAttributeName();
//
//        // OAuth2 서비스의 유저 정보들
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//
//        // registrationId에 따라 유저 정보를 통해 공통된 UserProfile 객체로 만들어 줌
//        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);
//
//        User user = saveOrUpdateUserProfile(userProfile);
//
//        return createDefaultOAuth2User(user, attributes, userNameAttributeName);
//    }
//
//    private User saveOrUpdateUserProfile(UserProfile userProfile) {
//        // OAuth에서의 유저 정보 변경이 있을 수 있기 때문에 DB에 update
//        User user = userRepository.findByOauthId(userProfile.getOauthId())
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_SOCIAL_USER));
//        if (user != null) {
//            user = user.updateOAuthInfo(userProfile.getOauthId(), userProfile.getUsersName(), userProfile.getUserEmail());
//        } else {
//            user = userRepository.save(userProfile.toUser());
//        }
//        return user;
//    }
//
//    private DefaultOAuth2User createDefaultOAuth2User(User user, Map<String, Object> attributes,
//                                                      String userNameAttributeName) {
//        return new DefaultOAuth2User(
//                Collections.singletonList(new SimpleGrantedAuthority("USER")),
//                attributes,
//                userNameAttributeName
//        );
//    }
//}