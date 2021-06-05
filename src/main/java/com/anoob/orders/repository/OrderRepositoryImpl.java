package com.anoob.orders.repository;

import com.anoob.orders.dto.request.OrderSearch;
import com.anoob.orders.dto.request.UpdateOrderDto;
import com.anoob.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderRepositoryImpl implements OrderTestRepositoryCustom {
    @Autowired
    MongoTemplate dbTemplate;

    @Override
    // String productId;
    //     String userId;
    //     String categoryid;
    //{"categoryid":"1","customer.userId":"1"} JSOn Syntax
    public List<Order> searchOrders(OrderSearch orderSearch) {
        Query searchquery = new Query();
        //String productid = orderSearch.getProductId();

        if(orderSearch.getCategoryid() !=null){
            searchquery.addCriteria(Criteria.where("categoryid").in(orderSearch.getCategoryid()));
        }

        if(orderSearch.getUserId() !=null){
            searchquery.addCriteria(Criteria.where("customer.userId").in(orderSearch.getUserId()));
        }
        //search by Order.OrderItem.productid
        if(orderSearch.getProductId() !=null){
            searchquery.addCriteria(Criteria.where("orderItems.productid").in(orderSearch.getProductId()));
        }

        return dbTemplate.find(searchquery, Order.class);


    }

    @Override
    public Order editOrder(String orderId, UpdateOrderDto updateOrderDto) {
        Query query = new Query(Criteria.where("_id").in(orderId));

        Update orderUpdates = new Update();
        boolean hasUpdates = false;
        for(Map.Entry<String, Object> entry: updateOrderDto.getUpdates().entrySet()){
            orderUpdates.set(entry.getKey(), entry.getValue());
            hasUpdates = true;
        }

        //orderUpdates.set("categoryid", "18");
        if(hasUpdates) {
            Order order = dbTemplate.findAndModify(query, orderUpdates, Order.class);

            return order;
        }

        return null;
    }
}
