package plus.log.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "userlogin")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    private Long userid;

    @Column(name="username",nullable = false, unique = true)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="token")
    private String token;

    @Column(name="last_login")
    private Date last_login;
}
