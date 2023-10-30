package egenius.user.infrastructure;

import egenius.user.entity.DogBreedList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogBreedRepository extends JpaRepository<DogBreedList, Long> {
}
