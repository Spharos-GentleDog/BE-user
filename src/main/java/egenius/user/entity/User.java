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
    @Column(name = "user_name", length = 20)
    private String userName;
    @Column(name = "user_age")
    private Integer userAge;
    @Column(name = "user_gender", columnDefinition = "tinyint")
    private Integer userGender;
    @Column(name = "user_phone_number", length = 15, nullable = false)
    private String userPhoneNumber;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void hashPassword(String password) {
        //      this.password = password;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getName() {
        return userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return true;
    }


}
