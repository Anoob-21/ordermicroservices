package com.anoob.orders.dto.response;

import com.anoob.orders.model.Customer;
import com.anoob.orders.model.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private String orderid;
    private Date createdDate;
    private Date updatedDate;
    private List<OrderItem> orderItems;
    private Customer customer;
    private String categoryid;
}
