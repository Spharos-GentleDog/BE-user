package egenius.user.infrastructure;

import egenius.user.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
