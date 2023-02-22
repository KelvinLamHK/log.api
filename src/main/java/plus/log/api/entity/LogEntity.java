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
@Table(name = "logTable")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="logId")
    private Long logId;

    @Column(name="token")
    private String token;

    @Column(name="deviceId")
    private String deviceId;

    @Column(name="lastLogin")
    private Date lastLogin;
}
