package egenius.dog.application;

import egenius.dog.dto.DogDefaultUpdateRequestDto;
import egenius.dog.dto.DogRegistrationRequestDto;
import egenius.dog.dto.DogSignUpRegistrationRequestDto;
import egenius.dog.dto.DogUpdateRequestDto;
import egenius.dog.entity.Dog;
import egenius.dog.entity.DogBreed;
import egenius.dog.entity.DogList;
import egenius.dog.infrastructure.DogBreedRepository;
import egenius.dog.infrastructure.DogListRepository;
import egenius.dog.infrastructure.DogRepository;
import egenius.dog.response.DogBreedInfoResponse;
import egenius.dog.response.DogInfoResponse;
import egenius.global.base.BaseResponseStatus;
import egenius.global.exception.BaseException;
import egenius.user.entity.User;
import egenius.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DogServiceImple implements DogService {

    /**
     * 1. 유저 회원가입 시 반려견 등록
     * 2. 유저 반려견 등록
     * 3. 반려견 전체 품종 조회
     * 4. 반려견 정보 조회
     * 5. 반려견 정보 수정
     * 6. 대표 반려견 변경
     * 7. 반려견 정보 삭제
     */

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final DogListRepository dogListRepository;
    private final DogBreedRepository dogBreedRepository;
    private final ModelMapper modelMapper;

    @Override
    public void signUpRegisterDog(DogSignUpRegistrationRequestDto dogSignUpRegistrationRequestDto) {
        User user = userRepository.findByUserEmail(dogSignUpRegistrationRequestDto.getUserEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        // 첫 회원가입 반려견은 true로 설정
        Dog dog = modelMapper.map(dogSignUpRegistrationRequestDto, Dog.class);

        // dogbreedId로 dogBreed 조회 및 dog에 저장
        DogBreed dogBreed = dogBreedRepository.findById(dogSignUpRegistrationRequestDto.getDogBreed())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DOG_BREED));
        dog.setDogBreed(dogBreed);
        dogRepository.save(dog);

        // dogList의 default값 변경
        DogList dogList = DogList.builder()
                .dog(dog)
                .user(user)
                .defaultDog(true)
                .build();

        dogListRepository.save(dogList);
    }

    /**
     * 1. 유저 반려견 등록
     * @param dogRegistrationRequestDto
     */
    @Override
    public void registerDog(String userEmail, DogRegistrationRequestDto dogRegistrationRequestDto) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        Dog dog = modelMapper.map(dogRegistrationRequestDto, Dog.class);

        // dogbreedId로 dogBreed 조회 및 dog에 저장
        DogBreed dogBreed = dogBreedRepository.findById(dogRegistrationRequestDto.getDogBreed())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DOG_BREED));
        dog.setDogBreed(dogBreed);
        dogRepository.save(dog);

        // dogRegistrationRequestDto getDefaultDog가 true이고 기존에 defaultDog가 true인 값이 있다면 false로 변경
        if (dogRegistrationRequestDto.getDefaultDog()) {
            DogList dogList = dogListRepository.findByUserIdAndDefaultDog(user.getId(), true);
            dogList.updateDefaultDog(false);
        }

        // dogList의 default값 변경
        DogList dogList = DogList.builder()
                .dog(dog)
                .user(user)
                .defaultDog(dogRegistrationRequestDto.getDefaultDog())
                .build();

        dogListRepository.save(dogList);
    }

    /**
     * 2. 반려견 전체 품종 조회
     *
     * @return
     */
    @Override
    public List<DogBreedInfoResponse> getDogBreedInfo() {
        log.info("dogBreedRepository.findAll() : {}", dogBreedRepository.findAll());
        return dogBreedRepository.findAll()
                .stream()
                .map(dogBreed -> modelMapper.map(dogBreed, DogBreedInfoResponse.class))
                .toList();
    }

    /**
     * 3. 반려견 정보 조회
     * @param userEmail
     * @return
     */
    @Override
    public List<DogInfoResponse> getDogInfo(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        // 유저의 반려견 리스트 조회
        List<DogList> dogList = dogListRepository.findByUserId(user.getId());

        // DogList 엔터티 리스트를 스트림으로 변환
        return dogList.stream()
                .map(item -> {
                    Dog myDog = item.getDog(); // DogList 엔터티에서 Dog 객체를 가져옴
                    // dogInfoResponse값 넣어줌
                    DogInfoResponse dogInfoResponse = modelMapper.map(myDog, DogInfoResponse.class);
                    // dogbreedname값 넣어줌
                    dogInfoResponse = dogInfoResponse.toBuilder()
                            .dogBreedKorName(myDog.getDogBreed().getDogBreedKorName())
                            .defaultDog(item.getDefaultDog())
                            .build();
                    return dogInfoResponse;
                })
                .toList();

    }

    /**
     * 4. 반려견 정보 수정
     * @param dogId
     * @param dogUpdateRequestDto
     */
    @Override
    public void updateDog(Long dogId, DogUpdateRequestDto dogUpdateRequestDto) {

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DOG));
        dog.updateDog(dogUpdateRequestDto);

    }

    /**
     * 5. 대표 반려견 변경
     * @param userEmail
     * @param dogDefaultUpdateRequestDto
     * @return
     */
    @Override
    public void updateRepresentativeDog(String userEmail, DogDefaultUpdateRequestDto dogDefaultUpdateRequestDto) {

        User user  = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        // 1. null이 아니라면 기존 대표 반려견 정보 조회 하고 false로 변경
        if (dogDefaultUpdateRequestDto.getOldDefaultDogId() != null) {
            DogList dogList = dogListRepository.findByUserIdAndDogId(user.getId(),
                    dogDefaultUpdateRequestDto.getOldDefaultDogId());
            dogList.updateDefaultDog(false);
        }

        // 2. newDogId로 dogList의 defaultDog를 true로 변경
        DogList dogList = dogListRepository.findByDogId(dogDefaultUpdateRequestDto.getNewDefaultDogId());
        dogList.updateDefaultDog(true);
    }

    /**
     * 6. 반려견 정보 삭제
     * @param dogId
     */
    @Override
    public void deleteDog(Long dogId) {

        /**
         * DogList에서 Foreign Key로 dogId를 참조하고 있기 때문에 dogId를 참조하는 DogList를 먼저 삭제해야 한다.
         * 그리고 DogList를 삭제하면 DogList의 Foreign Key로 dogId를 참조하고 있기 때문에 dogId를 참조하는 Dog를 삭제해야 한다.
         * 만일, Dog를 먼저 삭제하면 DogList의 Foreign Key로 dogId를 참조하고 있기 때문에 DogList를 삭제할 수 없다.
         */
        DogList dogList = dogListRepository.findByDogId(dogId);
        dogListRepository.delete(dogList);

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DOG));
        dogRepository.delete(dog);

    }
}
