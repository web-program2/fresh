package order_service.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;

    private String orderId;
    private String userId;

}
