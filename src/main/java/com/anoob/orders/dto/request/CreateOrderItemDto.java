package com.anoob.orders.dto.request;

import lombok.Data;

@Data
public class CreateOrderItemDto {
    private long productid;
    private int quantity;
}
