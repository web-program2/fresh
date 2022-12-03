package user_service.dto;

import user_service.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private Date createAt;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
