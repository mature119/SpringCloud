package com.zhm.springcloud.service;

import com.zhm.springcloud.entities.Pay;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PayService {
    public int pay(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);
    public Pay findById(Integer id);
    public List<Pay> findAll();
}
