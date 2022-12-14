package order_service.dto;

import lombok.Data;
import order_service.jpa.Order;
import order_service.vo.ResponseCatalog;
import order_service.vo.ResponseUser;

@Data
public class OrderCatalogUserDto {
    private Order order;
    private ResponseCatalog responseCatalog;
    private ResponseUser responseUser;

}
