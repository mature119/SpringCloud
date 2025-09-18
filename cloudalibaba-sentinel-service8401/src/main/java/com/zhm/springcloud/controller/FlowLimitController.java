package com.zhm.springcloud.controller;

import com.alibaba.csp.sentinel.util.TimeUtil;
import com.zhm.springcloud.service.FlowLimitService;
import io.micrometer.core.instrument.util.TimeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "------testB";
    }
    @Resource
    private FlowLimitService flowLimitService;
    @GetMapping("/testC")
    public String testC()
    {
        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD()
    {
        flowLimitService.common();
        return "------testD";
    }
}
