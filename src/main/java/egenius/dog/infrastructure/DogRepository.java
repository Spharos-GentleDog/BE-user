package egenius.dog.infrastructure;

import egenius.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findAllByDogBreedId(Long dogId);

}
