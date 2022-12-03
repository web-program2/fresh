package user_service.jpa;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="userTokens")
public class UserToken {
    @Id
    private Long userIdx;

    private String token;
}
