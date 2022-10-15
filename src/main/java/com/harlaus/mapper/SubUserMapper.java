package com.harlaus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harlaus.entity.SubUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubUserMapper extends BaseMapper<SubUser> {
    Integer doRegisterOne(@Param("subUser") SubUser subUser);

    Integer doDelOne(@Param("subUser") SubUser subUser);

    Integer doRestOne(@Param("subUser") SubUser subUser);

    SubUser getOne(@Param("subUser") SubUser subUser);

    SubUser getOneByPhone(@Param("subUser") SubUser subUser);
}
