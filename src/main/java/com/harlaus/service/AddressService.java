package com.harlaus.service;

import com.harlaus.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 23548
* @description 针对表【address】的数据库操作Service
* @createDate 2022-10-04 01:09:21
*/
public interface AddressService extends IService<Address> {
    Integer saveAddress(Address address);
    List<Address> queryAddress();
}
