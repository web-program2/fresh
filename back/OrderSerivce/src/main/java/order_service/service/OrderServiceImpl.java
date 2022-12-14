package order_service.service;

import order_service.client.CatalogServiceClient;
import order_service.client.UserServiceClient;
import order_service.dto.OrderCatalogUserDto;
import order_service.dto.OrderDto;
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

//    @Transactional
//    @Override
//    public OrderDto createOrder(OrderDto orderDetails) {
//        orderDetails.setOrderId(UUID.randomUUID().toString());
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        Order orderEntity = modelMapper.map(orderDetails, Order.class);
//        orderRepo.save(orderEntity);
//
//        OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);
//        return returnValue;
//    }

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

        OrderCatalogUserDto orderCatalogUserDto = new OrderCatalogUserDto();
        ResponseUser responseUser = userServiceClient.getResponseUser(order.getUserIdx());
        ResponseCatalog responseCatalog = catalogServiceClient.getResponseCatalog(order.getCatalogIdx());

        orderCatalogUserDto.setOrder(order);
        orderCatalogUserDto.setResponseUser(responseUser);
        orderCatalogUserDto.setResponseCatalog(responseCatalog);

        return orderCatalogUserDto;
    }

    @Transactional
    @Override
    public Order createOrder(Long userIdx, Long catalogIdx, Integer qty, Integer price) {
        // 1. catalog -> qty 만큼 stock 줄여서 업데이트해야됨.


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
