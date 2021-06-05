package com.anoob.orders.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
//Auto generate getters and setters
@Data
//For mapping to MongoDb.
@Document(collection = "orders")
public class Order {

    @Id
    private String orderid;
    private Date createdDate;
    private Date updatedDate;
    private List<OrderItem> orderItems;
    private Customer customer;
    private String categoryid;
}
