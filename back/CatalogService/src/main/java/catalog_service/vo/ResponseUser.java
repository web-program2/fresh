package catalog_service.vo;

import lombok.Data;

@Data
public class ResponseUser {
    private Long userIdx;
    private String id;
    private String pw;
    private String email;
    private String nickName;
    private String role;
}
