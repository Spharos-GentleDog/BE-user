package egenius.dog.infrastructure;

import egenius.dog.entity.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogBreedRepository extends JpaRepository<DogBreed, Long> {

    List<DogBreed> findAll();
}
