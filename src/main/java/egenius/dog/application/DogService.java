package egenius.dog.application;

import egenius.dog.dto.DogRegistrationRequestDto;
import egenius.dog.entity.DogBreed;
import egenius.dog.response.DogBreedInfoResponse;
import egenius.dog.response.DogInfoResponse;

import java.util.List;

public interface DogService {

    void registerDog(String userEmail, DogRegistrationRequestDto dogRegistrationRequestDto);
    List<DogBreedInfoResponse> getDogBreedInfo();
    List<DogInfoResponse> getDogInfo(String userEmail);
    void updateDog(Long dogListId, DogRegistrationRequestDto dogRegistrationRequestDto);
    void updateRepresentativeDog(String userEmail, Long ordDogId, Long newDogId);
    void deleteDog(String userEmail, Long dogListId);
}
