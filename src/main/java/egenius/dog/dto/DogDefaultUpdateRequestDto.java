package egenius.dog.dto;

import lombok.Getter;

@Getter
public class DogDefaultUpdateRequestDto {

    private Long oldDefaultDogId;
    private Long newDefaultDogId;
}
