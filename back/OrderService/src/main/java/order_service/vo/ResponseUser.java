package order_service.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class ResponseUser {
    private Long userIdx;
    private String id;
    private String pw;
    private String email;
    private String nickName;
    private String role;

}
