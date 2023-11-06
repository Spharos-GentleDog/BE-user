package egenius.dog.entity;

import egenius.global.base.BaseTimeEntity;
import egenius.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class DogList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "dog_id", referencedColumnName = "id", nullable = false)
    private Dog dog;

    @Column(name = "default_dog", columnDefinition = "boolean default false", nullable = false)
    private Boolean defaultDog;

    // 1. 유저의 기본 강아지 설정
    public void updateDefaultDog(Boolean defaultDog) {
        this.defaultDog = defaultDog;
    }
}
