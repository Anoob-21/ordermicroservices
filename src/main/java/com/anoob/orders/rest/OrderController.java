package com.anoob.orders.rest;


import com.anoob.orders.dto.request.CreateOrderDto;
import com.anoob.orders.dto.request.UpdateOrderItemDto;
import com.anoob.orders.dto.response.OrderDto;
import com.anoob.orders.dto.request.OrderSearch;
import com.anoob.orders.dto.request.UpdateOrderDto;
import com.anoob.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public void createOrder(@RequestBody CreateOrderDto createOrderDto){
        orderService.createOrder(createOrderDto);
    }


    ///order/5
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> retrieveOrderbyId(@PathVariable(value = "orderId") String orderId){
        OrderDto orderDto = orderService.retrieveOrderbyId(orderId);
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }

    @GetMapping("/orders")
    //Client will pass as query params for modelattribute/
    public ResponseEntity<List<OrderDto>> retrieveAllOrders(@ModelAttribute OrderSearch orderSearch){
        //(@RequestParam(value = "productId") String productId, @RequestParam(value = "quantity") long quantity){
       return  new ResponseEntity(orderService.searchOrders(orderSearch),HttpStatus.OK);

    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> editOrder(@RequestBody UpdateOrderDto updateOrderDto, @PathVariable String orderId){
        return new ResponseEntity(orderService.editOrder(updateOrderDto, orderId), HttpStatus.OK);

    }

    @PutMapping("/orders/{orderId}/orderItem/{productid}")
    public ResponseEntity<OrderDto> editOrderItem(@RequestBody UpdateOrderItemDto updateOrderItemDto, @PathVariable String orderId, @PathVariable Long productid){
        return new ResponseEntity(orderService.editOrderItem(updateOrderItemDto, orderId,productid), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder( @PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.OK);

    }


    @PostMapping("/orders/{orderId}/orderItem")
    public void addOrderItem(UpdateOrderDto updateOrderDto){
        //return null;

    }


}
