package com.anoob.orders.service;

import com.anoob.orders.dto.request.CreateOrderDto;
import com.anoob.orders.dto.request.OrderSearch;
import com.anoob.orders.dto.request.UpdateOrderDto;
import com.anoob.orders.dto.request.UpdateOrderItemDto;
import com.anoob.orders.dto.response.OrderDto;
import com.anoob.orders.mapper.CreateOrderDto_Order_Converter;
import com.anoob.orders.mapper.OrderDto_Order_Converter;
import com.anoob.orders.model.Order;
import com.anoob.orders.model.OrderItem;
import com.anoob.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    CreateOrderDto_Order_Converter createOrderDto_order_converter;
    @Autowired
    //All these are  managed by Spring, single instance.
    OrderDto_Order_Converter orderDto_order_converter;

    @Autowired
    OrderRepository orderRepository;

    //Not reqd as Order repo extends OrderRepoCustom
    //@Autowired
    //OrderRepositoryCustom searchrepository;

    public void createOrder(CreateOrderDto createOrder){
        Order order = createOrderDto_order_converter.convert(createOrder);

        orderRepository.save(order);
    }

    public OrderDto retrieveOrderbyId(String orderId) {
       // orderRepository.findOrderbyOrderid
      Order order= orderRepository.findByOrderid(orderId);
      OrderDto orderDto = orderDto_order_converter.convert(order);
      return orderDto;

    }

    public List<OrderDto> searchOrders(OrderSearch orderSearch) {
        //search by multiple criterias,if you don't pass anything,retrieve all orders .
        List<Order> orders = orderRepository.searchOrders(orderSearch);
        List<OrderDto> orderDtoList = orders
                .stream()
        //        .map(order -> orderDto_order_converter.convert(order))
                .map(orderDto_order_converter::convert)
                .collect(Collectors.toList());
        return orderDtoList;
    }

    public OrderDto editOrder(UpdateOrderDto updateOrderDto, String orderId) {
        Order order =  orderRepository.editOrder(orderId, updateOrderDto);
        return orderDto_order_converter.convert(order);
    }

    public void deleteOrder(String orderId) {
         orderRepository.deleteById(orderId);
    }

    public OrderDto editOrderItem(UpdateOrderItemDto updateOrderItemDto, String orderId, long productid) {
        Order order = orderRepository.findByOrderid(orderId);
        for (OrderItem item : order.getOrderItems()){
            if(item.getProductid() == productid){
                item.setQuantity(updateOrderItemDto.getQuantity());
            }
        }
       return orderDto_order_converter.convert(orderRepository.save(order)) ;
    }
}

