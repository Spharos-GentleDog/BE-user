package egenius.user.entity;

import egenius.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User extends BaseTimeEntity implements UserDetails {


    // DDD관점에서는 비즈니스 로직을 엔티티에 작성해도 괜찮음, 고려해볼것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id", length = 30, nullable = false)
    private String loginId;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;
    @Column(name = "address", columnDefinition = "int default 1")
    private Long address;

    public void hashPassword(String password) {
        //      this.password = password;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
