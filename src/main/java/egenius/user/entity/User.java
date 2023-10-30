package egenius.user.entity;

import egenius.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @Column(name = "user_email", length = 30)
    private String userEmail;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "phone_number", length = 15, nullable = false)
    private String phoneNumber;

    /**
     * CascadeType.Delete : User가 삭제되면 연관된 Entity도 삭제
     * orphanRemoval = true : User와 연관된 Entity가 없으면 삭제
     * 둘의 차이는 User가 삭제되면 연관된 Entity도 삭제되는 것은 같지만
     * orphanRemoval = true는 연관된 Entity가 없으면 삭제되는 것이 다름
     * 주소지 값을 변경해도 기존 주소지는 삭제하지 않으니 orphanRemoval = true를 사용하지 않음
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address favoriteAddress;

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
