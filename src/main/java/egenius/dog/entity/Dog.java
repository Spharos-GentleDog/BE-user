package egenius.dog.entity;

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

    @Column(name = "profile_image_url", length = 100)
    private String profileImageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_bread_id", referencedColumnName = "id")
    private DogBreed dogBreed;

}