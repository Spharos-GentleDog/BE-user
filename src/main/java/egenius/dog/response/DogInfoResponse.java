package egenius.dog.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DogInfoResponse {

    private String dogName;
    private Integer dogAge;
    private Integer dogGender;
    private Integer dogWeight;
    private String dogImageUrl;
    private String dogFurColor;
    private Integer dogBodyLength;
    private Integer dogBreastGirth;
    private Integer dogNeckGirth;
    private Integer dogLegLength;
    private String dogBreedKorName;

}
