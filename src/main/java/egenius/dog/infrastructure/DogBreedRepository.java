package egenius.dog.infrastructure;

import egenius.dog.entity.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogBreedRepository extends JpaRepository<DogBreed, Long> {

}
