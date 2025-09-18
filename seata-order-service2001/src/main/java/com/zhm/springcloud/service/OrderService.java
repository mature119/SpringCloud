package com.zhm.springcloud.service;

import com.zhm.springcloud.entities.Order;
import org.springframework.stereotype.Service;

public interface OrderService {
    public void createOrder(Order order);

}
