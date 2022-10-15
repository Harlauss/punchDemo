package com.harlaus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harlaus.entity.CommonResult;
import com.harlaus.entity.User;

import java.util.List;

/**
* @author 23548
* @description 针对表【user】的数据库操作Service
* @createDate 2022-09-25 16:02:51
*/
public interface UserService extends IService<User> {
    public CommonResult doRegister(List<User> users);
    public List<User> getAll();
    public User getOne(User user);
    public Integer delOne(User user);
    public Integer restOne(User user);
    public User getOneNoIsDel(User user);
}
