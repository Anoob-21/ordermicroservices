package com.anoob.orders.mapper;

import com.anoob.orders.dto.request.CreateOrderDto;
import com.anoob.orders.model.Order;
import com.anoob.orders.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateOrderDto_Order_Converter {
    public Order convert(CreateOrderDto createdOrderDto){
        Order newOrder = new Order();
        List<OrderItem> orderItems = createdOrderDto.getOrderItems().stream()
                .map(createOrderItemDto -> new OrderItem(createOrderItemDto.getProductid(), createOrderItemDto.getQuantity()))
                .collect(Collectors.toList());

        newOrder.setOrderItems(orderItems);
        newOrder.setCreatedDate(new Date());
        newOrder.setUpdatedDate(new Date());
        newOrder.setCategoryid(createdOrderDto.getCategoryid());
        newOrder.setCustomer(createdOrderDto.getCustomer());

        return newOrder;
    }
}
