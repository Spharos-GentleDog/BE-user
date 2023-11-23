package egenius.dog.infrastructure;

import egenius.dog.entity.DogList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DogListRepository extends JpaRepository<DogList, Long> {

    @Query("SELECT a FROM DogList a " +
            "WHERE a.user.id = :userId " +
            "ORDER BY a.defaultDog DESC")
    List<DogList> findByUserId(@Param("userId")Long userId);
    DogList findByUserIdAndDogId(Long userId, Long dogId);
    DogList findByUserIdAndDefaultDog(Long userId, Boolean defaultDog);
    DogList findByDogId(Long dogId);

}
