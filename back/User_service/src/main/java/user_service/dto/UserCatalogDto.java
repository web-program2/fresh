package user_service.dto;

import lombok.Data;
import user_service.jpa.User;
import user_service.vo.ResponseCatalog;

import java.util.List;

@Data
public class UserCatalogDto {
    private User user;
    private List<ResponseCatalog> catalogList;
    //temp
}
