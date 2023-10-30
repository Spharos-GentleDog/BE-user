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

    // DDD관점에서는 비즈니스 로직을 엔티티에 작성해도 괜찮음, 고려해볼것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_email", length = 30, nullable = false)
    private String userEmail;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;
    @Column(name = "address", columnDefinition = "int default 1")
    private Long address;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

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
