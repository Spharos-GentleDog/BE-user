package egenius.dog.presentation;

import egenius.dog.application.DogService;
import egenius.dog.dto.DogDefaultUpdateRequestDto;
import egenius.dog.dto.DogSignUpRegistrationRequestDto;
import egenius.dog.dto.DogUpdateRequestDto;
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

    /**
     * 1. 유저 회원가입 시 반려견 등록
     * 2. 유저 반려견 등록
     * 3. 반려견 전체 품종 조회
     * 4. 반려견 정보 조회
     * 5. 반려견 정보 수정
     * 6. 대표 반려견 변경
     * 7. 반려견 정보 삭제
     */

    @Operation(summary = "로그인 이전 반려견 등록", description = "로그인 이후 필터 필요없는 반려견 등록", tags = { "User Dog" })
    @PostMapping("/signup/")
    public BaseResponse<?> dogSignUpRegister(@RequestBody DogSignUpRegistrationRequestDto dogSignUpRegistrationRequestDto) {
        dogService.signUpRegisterDog(dogSignUpRegistrationRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "로그인 이후 반려견 등록", description = "로그인 이후 필터 필요한 반려견 등록", tags = { "User Dog" })
    @PostMapping("")
    public BaseResponse<?> dogRegister(@RequestHeader("userEmail") String userEmail,
                                       @RequestBody DogRegistrationRequestDto dogRegistrationRequestDto) {
        dogService.registerDog(userEmail, dogRegistrationRequestDto);
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
    public BaseResponse<List<DogInfoResponse>> dogInfo(@RequestHeader("userEmail") String userEmail) {
        List<DogInfoResponse> dogInfoResponse = dogService.getDogInfo(userEmail);
        log.info("dogInfoResponse : " + dogInfoResponse);
        return new BaseResponse<>(dogInfoResponse);
    }

    @Operation(summary = "반려견 정보 수정", description = "반려견 정보 수정", tags = { "User Dog" })
    @PutMapping("")
    public BaseResponse<?> dogUpdate(@RequestParam("dogListId") Long dogListId,
                                     @RequestBody DogUpdateRequestDto dogRegisterRequestDto) {
        dogService.updateDog(dogListId, dogRegisterRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "대표 반려견 변경", description = "현재 대표 반려견 false로 변경하고 새로운 대표 반려견 true 변경",
            tags = { "User Dog" })
    @PutMapping("/default")
    public BaseResponse<?> dogRepresentativeUpdate(@RequestHeader("userEmail") String userEmail,
                                                   @RequestBody DogDefaultUpdateRequestDto dogDefaultUpdateRequestDto) {
        dogService.updateRepresentativeDog(userEmail, dogDefaultUpdateRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "반려견 정보 삭제", description = "반려견 정보 삭제", tags = { "User Dog" })
    @DeleteMapping("")
    public BaseResponse<?> dogDelete(@RequestParam("dogId") Long dogId) {
        dogService.deleteDog(dogId);
        return new BaseResponse<>();
    }

}
