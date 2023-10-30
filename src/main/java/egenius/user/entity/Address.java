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
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_address", length = 100, nullable = false)
    private String userAddress;

    @Column(name = "address_name", length = 20, nullable = false)
    private String addressName;

    @Column(name = "entrance_password", length = 20)
    private String entrancePassword;

    private Long userId;

}