package com.anoob.orders.dto.request;

import com.anoob.orders.model.Customer;
import com.anoob.orders.model.OrderItem;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
//DTO's ,once some calls APi, he will provide createOrderDTO input
@Data
public class CreateOrderDto {

    private List<CreateOrderItemDto> orderItems;
    private Customer customer;
    private String categoryid;
}
