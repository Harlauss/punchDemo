package com.harlaus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harlaus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 23548
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-25 16:02:51
* @Entity com.harlaus.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    Integer doRegisterOne(@Param("user") User user);

    List<User> getAll();

    User getOne(@Param("user") User user);

    User getOneNoIsDel(@Param("user") User user);

    Integer delOne(@Param("user") User user);

    Integer restOne(@Param("user") User user);
}
