package egenius.dog.entity;

import egenius.dog.dto.DogRegistrationRequestDto;
import egenius.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Dog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dog_name", length = 20, nullable = false)
    private String dogName;

    @Column(name = "dog_age", nullable = false, columnDefinition = "tinyint")
    private Integer dogAge;

    @Column(name = "dog_gender", nullable = false, columnDefinition = "tinyint")
    private Integer dogGender;

    @Column(name = "dog_weight", nullable = false, columnDefinition = "tinyint")
    private Integer dogWeight;

    @Column(name = "dog_image_url", length = 100)
    private String dogImageUrl;

    @Column(name = "dog_fur_color", length = 10)
    private String dogFurColor;

    @Column(name = "dog_body_length", columnDefinition = "tinyint")
    private Integer dogBodyLength;

    @Column(name = "dog_breast_girth", columnDefinition = "tinyint")
    private Integer dogBreastGirth;

    @Column(name = "dog_neck_girth", columnDefinition = "tinyint")
    private Integer dogNeckGirth;

    @Column(name = "dog_leg_length", columnDefinition = "tinyint")
    private Integer dogLegLength;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_bread_id", referencedColumnName = "id", nullable = false)
    private DogBreed dogBreed;

    // 1. 강아지 정보 수정
    public void updateDog(DogRegistrationRequestDto dogRegistrationRequestDto){
        this.dogAge = dogRegistrationRequestDto.getDogAge();
        this.dogBodyLength = dogRegistrationRequestDto.getDogBodyLength();
        this.dogBreastGirth = dogRegistrationRequestDto.getDogBreastGirth();
        this.dogFurColor = dogRegistrationRequestDto.getDogFurColor();
        this.dogGender = dogRegistrationRequestDto.getDogGender();
        this.dogImageUrl = dogRegistrationRequestDto.getDogImageUrl();
        this.dogLegLength = dogRegistrationRequestDto.getDogLegLength();
        this.dogName = dogRegistrationRequestDto.getDogName();
        this.dogNeckGirth = dogRegistrationRequestDto.getDogNeckGirth();
        this.dogWeight = dogRegistrationRequestDto.getDogWeight();

    }

    public void setDogBreed(DogBreed dogBreed) {
        this.dogBreed = dogBreed;
    }
}