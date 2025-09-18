package com.zhm.springcloud.service.impl;

import com.zhm.springcloud.api.AccountFeignApi;
import com.zhm.springcloud.api.StorageFeignApi;
import com.zhm.springcloud.entities.Order;
import com.zhm.springcloud.mapper.OrderMapper;
import com.zhm.springcloud.resp.ResultData;
import com.zhm.springcloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
@GlobalTransactional(name="zhm-create-order",rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeignApi storageFeignApi;
    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    public void createOrder(Order order) {
        //Xid全局事务的检查
        String Xid  = RootContext.getXID();
        //新建订单
        log.info("------------------开始新建订单：" + "\t" + "xid" + Xid);
        //订单新建时默认初始订单状态为0
        order.setStatus(0);
        int result =  orderMapper.insertSelective(order);
        //插入订单成功过后获取插入成功的实体对象
        Order orderFromDB = null;
        if(result>0){
            orderFromDB = orderMapper.selectOne(order);
            log.info("------新建订单成功，orderFromDB info " + orderFromDB);
            //扣减库存
            log.info("------订单开始调用  Storage库存  做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            log.info("------订单结束调用  Storage库存  做扣减count完成");
            System.out.println();
            //扣减账户
            log.info("------订单开始调用  Account余额  做扣减金额");
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            log.info("------订单结束调用  Account余额  做扣减金额完成");
            System.out.println();
            //修改订单状态
            log.info("-------修改订单状态为1");
            orderFromDB.setStatus(1);
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB,whereCondition);
        }
        System.out.println();
        log.info("---------------结束新建订单" + "\t" + "xid" + Xid);
    }


}
