package com.zhm.springcloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.zhm.springcloud.api.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.micrometer.core.instrument.util.TimeUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;
    @RequestMapping(value = "/feign/pay/circuit/{id}",method = RequestMethod.GET)
    @CircuitBreaker(name="cloud-payment-service",fallbackMethod = "myCircuitFallback")
    public String payCircuit(@PathVariable("id") int id) {
        return payFeignApi.payCircuit(id);
    }
    public String myCircuitFallback(Throwable t) {
        return "myCircuitFallback,系统繁忙，请稍后再试---------/T o T/~~";
    }
    @RequestMapping(value = "/feign/pay/bulkhead/{id}",method = RequestMethod.GET)
    @Bulkhead(name = "cloud-payment-service",fallbackMethod ="myCircuitFallback1" ,type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> payBulkHead(@PathVariable("id") int id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "-----开始进入");

        System.out.println(Thread.currentThread().getName() + "\t" + "准备离开");
        return CompletableFuture.supplyAsync(() ->payFeignApi.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }
    public String myCircuitFallback1(Throwable t) {
        return "myBulkHeadFallback,系统繁忙，请稍后再试---------/T o T/~~";
    }

    @RequestMapping(value = "/feign/pay/ratelimit/{id}",method = RequestMethod.GET)
    @RateLimiter(name="cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public String payRatelimit(@PathVariable("id") int id) {
        return payFeignApi.myRatelimit(id);
    }
    public String myRatelimitFallback(Throwable t) {
        return "myRatelimitFallback,被限流了---------/T o T/~~";
    }



}
