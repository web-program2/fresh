package user_service.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputData {
    private String id;
    private String pw;
    private String nickName;
    private String email;
    private String role;
}
