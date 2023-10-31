package egenius.user.infrastructure;

import egenius.user.entity.DogList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogListRepository extends JpaRepository<DogList, Long> {

}
