package com.anoob.orders.mapper;

import com.anoob.orders.dto.request.CreateOrderDto;
import com.anoob.orders.dto.response.OrderDto;
import com.anoob.orders.model.Order;
import com.anoob.orders.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class OrderDto_Order_Converter {
    public OrderDto convert(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderid(order.getOrderid());
        orderDto.setCategoryid(order.getCategoryid());
        orderDto.setCreatedDate(order.getCreatedDate());
        orderDto.setCustomer(order.getCustomer());
        orderDto.setUpdatedDate(order.getUpdatedDate());
        orderDto.setOrderItems(order.getOrderItems());
        return orderDto;
    }
}
