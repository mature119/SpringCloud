package com.zhm.springcloud.controller;

import com.zhm.springcloud.entities.Order;
import com.zhm.springcloud.resp.ResultData;
import com.zhm.springcloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @RequestMapping ("/order/create")
    public ResultData<String> create(Order order) {
        orderService.createOrder(order);
        return ResultData.success("成功创建订单");
    }

}
