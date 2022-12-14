package order_service.service;

import order_service.dto.OrderDto;
import order_service.jpa.Order;
import lombok.RequiredArgsConstructor;
import order_service.jpa.OrderRepo;
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

    @Transactional
    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Order orderEntity = modelMapper.map(orderDetails, Order.class);
        orderRepo.save(orderEntity);

        OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);
        return returnValue;
    }

    @Override
    public List<Order> getOrderListbyUserIdx(Long userIdx) {
        List<Order> orderList= orderRepo.getOrderListbyUserIdx(userIdx);
        return orderList;
    }

}
