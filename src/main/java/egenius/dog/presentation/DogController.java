package egenius.dog.presentation;

import egenius.dog.application.DogService;
import egenius.dog.entity.DogBreed;
import egenius.dog.response.DogBreedInfoResponse;
import egenius.dog.response.DogInfoResponse;
import egenius.global.base.BaseResponse;
import egenius.dog.dto.DogRegistrationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/dog")
@Slf4j
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @Operation(summary = "반려견 등록", description = "반려견 등록", tags = { "User Dog" })
    @PostMapping("")
    public BaseResponse<?> dogRegister(Principal principal,
                                       @RequestBody DogRegistrationRequestDto dogRegistrationRequestDto) {
        dogService.registerDog(principal.getName(), dogRegistrationRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "반려견 품종 조회", description = "반려견 품종 조회", tags = { "User Dog" })
    @GetMapping("/breeds")
    public BaseResponse<List<DogBreedInfoResponse>> dogBreedInfo() {
        List<DogBreedInfoResponse> dogBreeds = dogService.getDogBreedInfo();
        return new BaseResponse<>(dogBreeds);
    }

    @Operation(summary = "반려견 정보 조회", description = "반려견 정보 조회", tags = { "User Dog" })
    @GetMapping("")
    public BaseResponse<List<DogInfoResponse>> dogInfo(Principal principal) {
        List<DogInfoResponse> dogInfoResponse = dogService.getDogInfo(principal.getName());
        log.info("dogInfoResponse : " + dogInfoResponse);
        return new BaseResponse<>(dogInfoResponse);
    }

    @Operation(summary = "반려견 정보 수정", description = "반려견 정보 수정", tags = { "User Dog" })
    @PutMapping("")
    public BaseResponse<?> dogUpdate(@RequestParam("dogListId") Long dogListId,
            @RequestBody DogRegistrationRequestDto dogRegisterRequestDto) {
        dogService.updateDog(dogListId, dogRegisterRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "대표 반려견 변경", description = "현재 대표 반려견 false로 변경하고 새로운 대표 반려견 true 변경",
            tags = { "User Dog" })
    @PutMapping("/representative")
    public BaseResponse<?> dogRepresentativeUpdate(Principal principal,
                                                   @RequestParam("oldDogId") Long oldDogId,
                                                   @RequestParam("newDogId") Long newDogId) {
        dogService.updateRepresentativeDog(principal.getName(), oldDogId, newDogId);
        return new BaseResponse<>();
    }

    @Operation(summary = "반려견 정보 삭제", description = "반려견 정보 삭제", tags = { "User Dog" })
    @DeleteMapping("")
    public BaseResponse<?> dogDelete(Principal principal,
                                     @RequestParam("dogListId") Long dogListId) {
        dogService.deleteDog(principal.getName(), dogListId);
        return new BaseResponse<>();
    }

}
