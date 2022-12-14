package order_service.vo;

import lombok.Data;

@Data
public class ResponseCatalog {
    private Long catalogIdx;
    private String name;
    private Integer stock;
    private Integer price;
    private Integer userIdx;
}
