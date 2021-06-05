package com.anoob.orders.repository;

import com.anoob.orders.dto.request.OrderSearch;
import com.anoob.orders.dto.request.UpdateOrderDto;
import com.anoob.orders.model.Order;

import java.util.List;

public interface OrderTestRepositoryCustom {
    List<Order> searchOrders(OrderSearch orderSearch) ;
    Order editOrder(String orderId, UpdateOrderDto updateOrderDto) ;
}
