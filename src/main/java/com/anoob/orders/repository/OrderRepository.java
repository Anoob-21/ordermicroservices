package com.anoob.orders.repository;

import com.anoob.orders.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
//Combining repositories.
public interface OrderRepository extends MongoRepository<Order,String>, OrderTestRepositoryCustom {
    List<Order> findAllByOrderid(String orderid);
    Order findByOrderid(String orderid);
}
//Won't work if order repository changed to order test repo,follows naming convention ,entity+repo name.
//MongoRepository           OrderRepositoryCustom

//            OrderRepository              OrderRepositoryImpl