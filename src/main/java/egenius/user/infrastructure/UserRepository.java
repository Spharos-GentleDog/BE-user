package egenius.user.infrastructure;

import egenius.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String userEmail);
//    Optional<User> findByOauthId(Long oauthId);
    Optional<User> findByUserPhoneNumber(String userPhoneNumber);

}
