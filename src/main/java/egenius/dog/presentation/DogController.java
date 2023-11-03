package egenius.dog.presentation;

import egenius.global.base.BaseResponse;
import egenius.dog.dto.DogRegistrationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user/dog")
@Slf4j
@RequiredArgsConstructor
public class DogController {

    @Operation(summary = "반려견 등록", description = "반려견 등록", tags = { "User Dog" })
    @PostMapping("")
    public BaseResponse<?> dogRegister(@RequestBody DogRegistrationRequestDto dogRegisterRequestDto) {

        return new BaseResponse<>();
    }

    @Operation(summary = "반려견 정보 조회", description = "반려견 정보 조회", tags = { "User Dog" })
    @GetMapping("")
    public BaseResponse<?> dogInfo(Principal principal) {

        return new BaseResponse<>();
    }

    @Operation(summary = "반려견 정보 수정", description = "반려견 정보 수정", tags = { "User Dog" })
    @PutMapping("")
    public BaseResponse<?> dogUpdate(@RequestBody DogRegistrationRequestDto dogRegisterRequestDto) {

        return new BaseResponse<>();
    }

    @Operation(summary = "반려견 정보 삭제", description = "반려견 정보 삭제", tags = { "User Dog" })
    @DeleteMapping("")
    public BaseResponse<?> dogDelete(Principal principal,
                                     @RequestParam("dogId") Long dogId) {

        return new BaseResponse<>();
    }

}
