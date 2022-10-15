package com.harlaus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harlaus.config.JedisConfig;
import com.harlaus.entity.Address;
import com.harlaus.service.AddressService;
import com.harlaus.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23548
* @description 针对表【address】的数据库操作Service实现
* @createDate 2022-10-04 01:09:21
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Integer saveAddress(Address address) {
        return addressMapper.insertAddress(address);
    }

    @Override
    public List<Address> queryAddress() {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get("address");
        List<Address> addressList;
        if(StringUtils.isBlank(s)){
            addressList = this.baseMapper.selectList(new QueryWrapper<Address>());
            String jsonString = JSONObject.toJSONString(addressList);
            jedis.set("address",jsonString);
        }else {
            addressList = JSONObject.parseArray(s,Address.class);
        }
        List<Address> provinceList = new ArrayList<>();
        addressList.forEach(address -> {
            address.setChildren(new ArrayList<>());
            if(address.getParentId() == null){
                provinceList.add(address);
            }
        });
        provinceList.forEach(province -> {
            addressList.forEach(address -> {
                if(address.getParentId() != null && address.getParentId().equals(province.getId())){
                    addressList.forEach(city->{
                        if(city.getParentId() != null && city.getParentId().equals(address.getId())){
                            city.setChildren(null);
                            address.getChildren().add(city);
                        }
                    });
                    province.getChildren().add(address);
                }
            });
        });
        return provinceList;
    }
}




