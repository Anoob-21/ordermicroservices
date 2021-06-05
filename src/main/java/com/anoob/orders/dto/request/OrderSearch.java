package com.anoob.orders.dto.request;

import lombok.Data;

@Data
//Case sensitive - should match query string params.
public class OrderSearch {
    String productId;
     String userId;
     String categoryid;

}
