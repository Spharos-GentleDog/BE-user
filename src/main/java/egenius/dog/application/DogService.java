package egenius.dog.application;

import egenius.dog.dto.DogDefaultUpdateRequestDto;
import egenius.dog.dto.DogRegistrationRequestDto;
import egenius.dog.dto.DogUpdateRequestDto;
import egenius.dog.entity.DogBreed;
import egenius.dog.response.DogBreedInfoResponse;
import egenius.dog.response.DogInfoResponse;

import java.util.List;

public interface DogService {

    void registerDog(String userEmail, DogRegistrationRequestDto dogRegistrationRequestDto);
    List<DogBreedInfoResponse> getDogBreedInfo();
    List<DogInfoResponse> getDogInfo(String userEmail);
    void updateDog(Long dogListId, DogUpdateRequestDto dogUpdateRequestDto);
    void updateRepresentativeDog(String userEmail, DogDefaultUpdateRequestDto dogDefaultUpdateRequestDto);
    void deleteDog(Long dogId);
}
