package egenius.user.entity;

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

    @Column(name = "dog_weight", nullable = false, columnDefinition = "tinyint")
    private Integer dogWeight;

    @Column(name = "dog_fur_color", length = 10, nullable = false)
    private String dogFurColor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_bread_id", referencedColumnName = "id")
    private DogBreedList dogBreedList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

}