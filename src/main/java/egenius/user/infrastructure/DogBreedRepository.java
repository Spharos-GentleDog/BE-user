package egenius.user.infrastructure;


import egenius.user.entity.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogBreedRepository extends JpaRepository<DogBreed, Long> {

}
