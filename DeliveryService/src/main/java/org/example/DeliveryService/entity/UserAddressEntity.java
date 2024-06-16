package org.example.DeliveryService.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(
    name = "user_address",
    indexes = {@Index(name = "idx_user_address_user_id", columnList = "user_id")}
)
@Entity
public class UserAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_address_id")
    private Long userAddressId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address")
    private String address;

    @Column(name = "alias")
    private String alias;

    protected UserAddressEntity() {}

    @Builder
    public UserAddressEntity(Long userId, String address, String alias) {
        this.userId = userId;
        this.address = address;
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAddressEntity that)) {
            return false;
        }
        return this.getUserAddressId() != null && this.getUserAddressId().equals(that.getUserAddressId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserAddressId());
    }

}
