package order_service.service;

import order_service.client.CatalogServiceClient;
import order_service.client.UserServiceClient;
import order_service.dto.OrderCatalogUserDto;
import order_service.dto.OrderDto;
import order_service.dto.OrderqtyDto;
import order_service.jpa.Order;
import lombok.RequiredArgsConstructor;
import order_service.jpa.OrderRepo;
import order_service.vo.ResponseCatalog;
import order_service.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;

    private final CatalogServiceClient catalogServiceClient;

    private final UserServiceClient userServiceClient;

    @Override
    public List<Order> getOrderListbyUserIdx(Long userIdx) {
        List<Order> orderList= orderRepo.getOrderListbyUserIdx(userIdx);
        return orderList;
    }

    @Override
    public OrderCatalogUserDto getOrder(Long orderIdx) {
        List<Order> orderList = orderRepo.getOrderListbyOrderIdx(orderIdx);
        Order order = null;
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderIdx() == orderIdx) {
                order = orderList.get(i);
            }
        }
        System.out.println(order);

        OrderCatalogUserDto orderCatalogUserDto = new OrderCatalogUserDto();

        ResponseCatalog responseCatalog = catalogServiceClient.getResponseCatalog(order.getCatalogIdx());
        System.out.println(responseCatalog);

        orderCatalogUserDto.setOrder(order);
        orderCatalogUserDto.setResponseCatalog(responseCatalog);

        return orderCatalogUserDto;
    }

    @Transactional
    @Override
    public Order createOrder(Long userIdx, Long catalogIdx, Integer qty, Integer price) {
        // 1. catalog -> qty 만큼 stock 줄여서 업데이트해야됨.
        OrderqtyDto orderqtyDto = new OrderqtyDto();
        orderqtyDto.setCatalogIdx(catalogIdx);
        orderqtyDto.setQty(qty);

        catalogServiceClient.requestStock(orderqtyDto);

        // 2. 저장
        Order order = new Order();
        order.setCatalogIdx(catalogIdx);
        order.setUserIdx(userIdx);
        order.setQty(qty);
        order.setUnitPrice(price);
        orderRepo.createOrder(order);

        return null;
    }

}
