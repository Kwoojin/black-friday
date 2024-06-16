package org.example.MemberService.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column(name = "user_name")
    private String userName;

    protected UserEntity() {}

    public UserEntity(String loginId, String userName) {
        this.loginId = loginId;
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity that)) {
            return false;
        }
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }


}
