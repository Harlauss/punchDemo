package com.harlaus.controller;

import com.harlaus.config.JedisConfig;
import com.harlaus.entity.Address;
import com.harlaus.entity.CommonResult;
import com.harlaus.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/query")
    public CommonResult queryAddress(){
        List<Address> addressList = addressService.queryAddress();
        if(addressList.size() > 0){
            return new CommonResult(200,"地址获取成功",addressList);
        }else {
            return new CommonResult(500,"地址获取失败",null);
        }
    }
}
