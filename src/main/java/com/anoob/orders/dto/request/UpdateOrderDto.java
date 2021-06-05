package com.anoob.orders.dto.request;

import com.anoob.orders.model.Customer;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class UpdateOrderDto {
    Optional<List<String>> orderItems;
    Optional<Customer> customer;
    Optional<String> categoryid;

    public Map<String, Object> getUpdates(){

        try {
            Map<String,Object> updatedFields = new HashMap<>();
            for(Field field: this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(this);
                if(value instanceof Optional) {
                    updatedFields.put(field.getName(), ((Optional)value).orElse(null));
                }
            }
            return updatedFields;

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to determine fields for update", e);
        }

    }
}
