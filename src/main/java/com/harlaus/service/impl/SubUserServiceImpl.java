package com.harlaus.service.impl;

import com.harlaus.entity.SubUser;
import com.harlaus.entity.User;
import com.harlaus.mapper.SubUserMapper;
import com.harlaus.mapper.UserMapper;
import com.harlaus.service.SubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubUserServiceImpl implements SubUserService {

    @Autowired
    private SubUserMapper subUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String doRegister(SubUser subUser,User user) {
        User userMapperOne = userMapper.getOne(user);
        Integer count = 0;
        if(userMapperOne != null){
            SubUser one = subUserMapper.getOne(subUser);
            if(one == null){
                count = subUserMapper.doRegisterOne(subUser);
            }else {
                if(one.getIsSub()){
                   count = subUserMapper.doRestOne(subUser);
                }else {
                    return "已经订阅过啦,不要再订阅啦";
                }
            }
        }else{
            return "请先注册进系统中再订阅";
        }
        if(count == 1){
            return "订阅成功";
        }else {
            return "未知错误,请通知我的主人";
        }
    }
}
