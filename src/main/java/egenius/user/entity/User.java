package egenius.user.entity;

import egenius.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_email", length = 30)
    private String userEmail;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "users_name", length = 20)
    private String usersName;
    @Column(name = "user_age")
    private Integer userAge;
    @Column(name = "user_gender", columnDefinition = "tinyint")
    private Integer userGender;
    @Column(name = "user_phone_number", length = 15, nullable = false)
    private String userPhoneNumber;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
//    @Column(name = "provider", length = 10)
//    private String provider; //어떤 OAuth인지(google, naver 등)
//    @Column(name = "provide_id", length = 30)
//    private String provideId; // 해당 OAuth 의 key(id)


    /**
     * 도메인 로직
     * 1. 비밀번호 암호화
     * 2. 회원정보 수정 : userEmail, usersName, userPhoneNumber, userAge, userGender 변경
     * 3. 유저 탈퇴: deletedAt 변경
     *
     */

    // 1. 비밀번호 암호화
    public void hashPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    // 2. 회원 정보 수정
    public void updateUserInfo(String userEmail,
                               String usersName,
                               String userPhoneNumber,
                               Integer userAge,
                               Integer userGender) {

        this.userEmail = userEmail;
        this.usersName = usersName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAge = userAge;
        this.userGender = userGender;
    }

    // 3. 유저 탈퇴
    public void deactivateUser() {
        this.deletedAt = LocalDateTime.now();
    }

//    // 4. OAuth 회원가입
//    public User updateOAuthInfo(String usersName, String password, String userEmail, String provider, String provideId) {
//        this.usersName = usersName;
//        this.password = password;
//        this.userEmail = userEmail;
//        this.provider = provider;
//        this.provideId = provideId;
//        return this;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // todo: 1년 동안 사용하지 않으면 휴면 계정으로 바꾼다.
        // 현재 시간 - 마지막 로그인 시간 => 1년을 초기하면 return false로 바꾼다.
        return true;
    }


}
