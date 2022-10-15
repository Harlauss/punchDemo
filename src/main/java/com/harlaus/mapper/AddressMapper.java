package com.harlaus.mapper;

import com.harlaus.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 23548
* @description 针对表【address】的数据库操作Mapper
* @createDate 2022-10-04 01:09:21
* @Entity com.harlaus.entity.Address
*/
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    Integer insertAddress(@Param("address") Address address);
}




