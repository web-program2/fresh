package order_service.dto;

import lombok.Data;

@Data
public class CreateOrderDTO {
    private Long userIdx;
    private Long catalogIdx;
    private Integer qty;
    private Integer price;

}
