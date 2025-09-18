package com.zhm.springcloud.service;

import com.zhm.springcloud.entities.Pay;
import com.zhm.springcloud.mapper.PayMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService
{
    @Resource
    private PayMapper payMapper;
    @Override
    public int pay(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay findById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> findAll() {
        List<Pay> list = payMapper.selectAll();
        return list;
    }
}
