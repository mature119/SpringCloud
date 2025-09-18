package com.zhm.springcloud.controller;
import com.zhm.springcloud.entities.Pay;
import com.zhm.springcloud.entities.PayDTO;
import com.zhm.springcloud.resp.ResultData;
import com.zhm.springcloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Slf4j
@Tag(name="支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;
    @Operation(summary = "新增",description = "新增支付流水方法，json串作为参数")
    @RequestMapping(value = "/pay/payAdd",method = RequestMethod.POST)
    public ResultData<String> addPay(@RequestBody Pay pay) {
        int i = payService.pay(pay);
        return ResultData.success("成功插入记录：" + i);
    }

    @Operation(summary = "删除",description = "删除支付流水方法，id作为参数")
    @RequestMapping(value = "/pay/deletePay/{id}",method = RequestMethod.DELETE)
    public ResultData<String> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success("成功删除记录，返回值" + i);
    }

    @Operation(summary = "更新",description = "更新支付流水方法，json串作为参数")
    @RequestMapping(value = "/pay/updatePay",method = RequestMethod.POST)
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int i = payService.update(pay);
        return ResultData.success("成功更新记录，返回值："+ i);
    }

    @Operation(summary = "根据id查找",description = "查找支付流水方法，id作为参数")
    @RequestMapping(value = "/pay/selectById/{id}",method = RequestMethod.GET)
    public ResultData<String> selectById(@PathVariable("id") Integer id) {
        Pay pay = payService.findById(id);

        return ResultData.success("成功查找到记录："+pay);
    }

    @Operation(summary = "查找全部",description = "查找全部支付流水方法")
    @RequestMapping(value = "/pay/selectAll",method = RequestMethod.GET)
    public ResultData<String >selectAll() {
        List<Pay> list = payService.findAll();
        for(Pay pay : list){
            System.out.println(pay);
        }
        return ResultData.success("成功返回记录:" + list.size() + "条");
    }
    @Value("${server.port}")
    private String port;
    @RequestMapping(value = "/pay/get/info",method = RequestMethod.GET)
    public String getInfoByConsul(@Value("${zhm.info}") String zhmInfo) {
        return "zhmInfo:" + zhmInfo + "port:" + port;
    }
}
