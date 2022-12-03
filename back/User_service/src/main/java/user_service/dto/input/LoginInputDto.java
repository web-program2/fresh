package user_service.dto.input;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {
    private String id;
    private String pw;
    private boolean isForce;
}
