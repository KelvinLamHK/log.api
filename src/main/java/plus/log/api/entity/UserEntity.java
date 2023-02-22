package plus.log.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "userTable")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Long userId;

    @Column(name="username",nullable = false, unique = true)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="logId")
    private Long logId;
}
