package egenius.dog.application;

import egenius.dog.dto.DogDefaultUpdateRequestDto;
import egenius.dog.dto.DogRegistrationRequestDto;
import egenius.dog.dto.DogUpdateRequestDto;
import egenius.dog.response.DogBreedInfoResponse;
import egenius.dog.response.DogDefaultInfoResponse;
import egenius.dog.response.DogInfoResponse;
import egenius.dog.dto.DogSignUpRegistrationRequestDto;

import java.util.List;

public interface DogService {

    void signUpRegisterDog(DogSignUpRegistrationRequestDto dogSignUpRegistrationRequestDto);
    void registerDog(String userEmail, DogRegistrationRequestDto dogRegistrationRequestDto);
    List<DogBreedInfoResponse> getDogBreedInfo();
    DogInfoResponse getDogInfo(String userEmail, Long dogId);
    List<DogInfoResponse> getDogInfo(String userEmail);
    void updateDog(Long dogListId, DogUpdateRequestDto dogUpdateRequestDto);
    DogDefaultInfoResponse updateRepresentativeDog(String userEmail, DogDefaultUpdateRequestDto dogDefaultUpdateRequestDto);
    void deleteDog(Long dogId);
}
