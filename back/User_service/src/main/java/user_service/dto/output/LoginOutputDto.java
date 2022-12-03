package user_service.dto.output;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOutputDto {
    private String accessToken;
    private String refreshToken;

}
