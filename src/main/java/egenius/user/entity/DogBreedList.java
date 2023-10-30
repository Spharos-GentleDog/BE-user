package egenius.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class DogBreedList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dog_breed_kor_name", length = 30, nullable = false)
    private String dogBreedKorName;

    @Column(name = "dog_breed_eng_name", length = 30, nullable = false)
    private String dogBreedEngName;

}