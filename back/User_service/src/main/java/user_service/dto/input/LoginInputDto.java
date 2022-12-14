package user_service.dto.input;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {
    private String id;
    private String pw;
    private boolean isForce;

    @JsonProperty("isForce")
    public boolean getIsForce() {
        return this.isForce;
    }

}
