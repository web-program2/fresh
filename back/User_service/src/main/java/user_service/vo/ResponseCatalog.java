package user_service.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseCatalog {
    private Long catalogIdx;
    private String name;
    private Integer stock;
    private Integer price;
    private Integer userIdx;
}
