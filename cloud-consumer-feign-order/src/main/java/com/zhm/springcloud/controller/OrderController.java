package com.zhm.springcloud.controller;

import cn.hutool.core.date.DateUtil;
import com.zhm.springcloud.api.PayFeignApi;
import com.zhm.springcloud.entities.PayDTO;
import com.zhm.springcloud.resp.ResultData;
import com.zhm.springcloud.resp.ReturnCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Tag(name = "订单模块管理")
@RestController

public class OrderController {
   @Resource
    private PayFeignApi payFeignApi;
   @RequestMapping(value = "/feign/pay/payAdd",method = RequestMethod.POST)
   public ResultData<String> addOrder(@RequestBody PayDTO payDTO) {
       ResultData<String> resultData = payFeignApi.addPay(payDTO);

       return resultData;
   }
   @RequestMapping(value = "/feign/pay/selectById/{id}",method = RequestMethod.GET)
    public ResultData selectById(@PathVariable("id") Integer id) {
       ResultData resultData = null;
       try{
           System.out.println("开始调用######"+DateUtil.now());
           resultData = payFeignApi.selectById(id);
        }catch (Exception e){
           System.out.println("调用结束！！！！！！"+DateUtil.now());
           e.printStackTrace();
           ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }

       return resultData;
   }
   @RequestMapping(value = "/feign/pay/get/info",method = RequestMethod.GET)
    public String getInfo(){
      String info =  payFeignApi.getPayInfo();
      return info;
   }
}
