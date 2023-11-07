package egenius.dog.infrastructure;

import egenius.dog.entity.DogList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogListRepository extends JpaRepository<DogList, Long> {

    List<DogList> findByUserId(Long userId);
    DogList findByUserIdAndDogId(Long userId, Long dogId);
    DogList findByUserIdAndDefaultDog(Long userId, Boolean defaultDog);
    DogList findByDogId(Long dogId);

}
