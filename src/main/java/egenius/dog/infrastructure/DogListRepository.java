package egenius.dog.infrastructure;

import egenius.dog.entity.DogList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogListRepository extends JpaRepository<DogList, Long> {

}
