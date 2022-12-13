package user_service.jpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class UserToken {
    private String grantType; //jwt 인증타입
    private String accessToken;
    private String refreshToken;
}

