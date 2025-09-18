package com.zhm.springcloud.controller;

import com.zhm.springcloud.entities.PayDTO;
import com.zhm.springcloud.resp.ResultData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "订单模块管理")
@RestController
@Slf4j
public class OrderController {
    //public static final String Payment_Url = "http://localhost:8001";
    public static final String Payment_Url = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate ;
    @RequestMapping(value = "/consumer/pay/payAdd",method = RequestMethod.GET)
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(Payment_Url + "/pay/payAdd",payDTO,ResultData.class);
    }
    @RequestMapping(value = "/consumer/pay/updatePay",method = RequestMethod.GET)
    public ResultData updateOrder(PayDTO payDTO) {
        return restTemplate.postForObject(Payment_Url + "/pay/updatePay",payDTO,ResultData.class);
    }
    @RequestMapping(value = "/consumer/pay/deletePay/{id}",method = RequestMethod.DELETE)
    public ResultData deletePay(@PathVariable("id") int id) {
        return restTemplate.exchange(Payment_Url + "/pay/deletePay/{id}", HttpMethod.DELETE, null, ResultData.class, id).getBody();
    }
    @RequestMapping(value = "/consumer/pay/selectById/{id}",method = RequestMethod.GET)
    public ResultData selectById(@PathVariable("id") int id) {
        return restTemplate.getForObject(Payment_Url + "/pay/selectById/"+ id,ResultData.class);
    }
    @RequestMapping(value = "/consumer/pay/get/info",method = RequestMethod.GET)
    public String getInfo() {
        return restTemplate.getForObject(Payment_Url + "/pay/get/info",String.class);
    }
}
