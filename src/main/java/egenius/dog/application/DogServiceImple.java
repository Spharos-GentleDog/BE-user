package egenius.dog.application;

import egenius.dog.dto.DogRegistrationRequestDto;
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
     * 1. 유저 반려견 등록
     * 2. 반려견 전체 품종 조회
     * 3. 반려견 정보 조회
     * 4. 반려견 정보 수정
     * 5. 대표 반려견 변경
     * 6. 반려견 정보 삭제
     */

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final DogListRepository dogListRepository;
    private final DogBreedRepository dogBreedRepository;
    private final ModelMapper modelMapper;

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

        // 유저 반려견 등록
        dogRepository.save(dog);

        // 유저 반려견 리스트에 값이 있는지 조회
        Boolean dogList = dogListRepository.existsByUserId(user.getId());

        // 유저 반려견이 없다면 처음 등록하는 반려견을 대표 반려견으로 등록
        if (!dogList) {
            DogList dogList1 = DogList.builder()
                    .dog(dog)
                    .user(user)
                    .defaultDog(true)
                    .build();
            dogListRepository.save(dogList1);
        } else {
            // 유저 반려견이 있다면 다음 반려견은 false로 등록
            DogList dogList2 = DogList.builder()
                    .dog(dog)
                    .user(user)
                    .defaultDog(false)
                    .build();
            dogListRepository.save(dogList2);
        }
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


        List<DogList> dogList = dogListRepository.findByUserId(user.getId());
        // DogList 엔터티 리스트를 스트림으로 변환
        return dogList.stream()
                .map(dogList1 -> {
                    Dog myDog = dogList1.getDog(); // DogList 엔터티에서 Dog 객체를 가져옴
                    // dogInfoResponse값 넣어줌
                    DogInfoResponse dogInfoResponse = modelMapper.map(myDog, DogInfoResponse.class);
                    // dogbreedname값 넣어줌
                    dogInfoResponse = dogInfoResponse.toBuilder()
                            .dogBreedKorName(myDog.getDogBreed().getDogBreedKorName())
                            .defaultDog(dogList1.getDefaultDog())
                            .build();
                    return dogInfoResponse;
                })
                .toList();

    }

    /**
     * 4. 반려견 정보 수정
     * @param dogRegistrationRequestDto
     */
    @Override
    public void updateDog(Long dogListId, DogRegistrationRequestDto dogRegistrationRequestDto) {
        DogList dogList = dogListRepository.findByDogId(dogListId);
        Dog dog = dogList.getDog(); // DogList 엔터티에서 Dog 객체를 가져옴
        dog.updateDog(dogRegistrationRequestDto);
    }

    /**
     * 5. 대표 반려견 변경
     * @param userEmail
     * @param ordDogId
     * @param newDogId
     */
    @Override
    public void updateRepresentativeDog(String userEmail, Long ordDogId, Long newDogId) {

        User user  = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        // 1. null이 아니라면 기존 대표 반려견 정보 조회 하고 false로 변경
        if (ordDogId != null) {
            DogList dogList = dogListRepository.findByUserIdAndDogId(user.getId(), ordDogId);
            dogList.updateDefaultDog(false);
        }

        // 2. 새로운 대표 반려견 정보 조회 하고 true로 변경
        DogList dogList = dogListRepository.findByUserIdAndDogId(user.getId(), newDogId);
        dogList.updateDefaultDog(true);

    }

    /**
     * 6. 반려견 정보 삭제
     * @param userEmail
     * @param dogListId
     */
    @Override
    public void deleteDog(String userEmail, Long dogListId) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        DogList dogList = dogListRepository.findByUserIdAndDogId(user.getId(), dogListId);
        // 삭제한 반려견이 기본 반려견이라면 다음 반려견을 기본 반려견으로 변경
        if (dogList.getDefaultDog()) {
            DogList nextDogList = dogListRepository.findByUserId(user.getId())
                    .stream() // DogList 엔터티 리스트를 스트림으로 변환
                    .filter(dogList1 -> !dogList1.getId().equals(dogListId)) // 기본 반려견이 아닌 반려견을 필터링
                    .findFirst()
                    .orElse(null); // 첫번째 반려견을 가져옴 없다면 null

            // 첫번째 반려견이 있다면 true로 변경
            if (nextDogList != null)
                // 첫번째 반려견을 기본 반려견으로 변경
                nextDogList.updateDefaultDog(true);
        }

        dogListRepository.delete(dogList);
    }
}
