package com.anoob.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private long productid;
    private int quantity;
}
