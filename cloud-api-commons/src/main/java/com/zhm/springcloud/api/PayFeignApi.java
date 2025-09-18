package com.zhm.springcloud.api;

import com.zhm.springcloud.entities.PayDTO;
import com.zhm.springcloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {
    @RequestMapping(value = "/pay/payAdd",method = RequestMethod.POST)
    public ResultData<String> addPay(@RequestBody PayDTO payDTO);
    @RequestMapping(value = "/pay/selectById/{id}",method = RequestMethod.GET)
    public ResultData selectById(@PathVariable("id") Integer id);
    @RequestMapping(value = "/pay/get/info",method = RequestMethod.GET)
    public String getPayInfo();
    @RequestMapping(value = "/pay/circuit/{id}")
    public String payCircuit(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);
    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();

}
