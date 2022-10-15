package com.harlaus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harlaus.entity.CommonResult;
import com.harlaus.entity.User;
import com.harlaus.service.AutoPunchService;
import com.harlaus.service.UserService;
import com.harlaus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23548
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-25 16:02:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

    @Autowired
    private AutoPunchService autoPunchService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonResult doRegister(List<User> users) {
        List<String> passwordMistake = new ArrayList<>(); //账号密码错误
        List<String> exist = new ArrayList<>(); //数据库中已经存在的
        List<String> fail = new ArrayList<>(); //因为特殊原因导致失败的
        List<String> success = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            try {
                Integer count = 0;
                User user = users.get(i);
                if(autoPunchService.getLoginToken(user) == null){
                    passwordMistake.add(user.getPhone() + "    序号为" + i);
                }else {
                    if(getOne(user) != null){
                        exist.add(user.getPhone() + "    序号为" + i);
                    }else {
                        if(getOneNoIsDel(user) != null){
                            count = restOne(user);
                        }else {
                            count = this.baseMapper.doRegisterOne(user);
                        }
                        if(count == 1){
                            success.add(user.getPhone() + "    序号为" + i);
                        }else {
                            fail.add(user.getPhone() + "    序号为" + i);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(passwordMistake.size() == 0 && exist.size() == 0 && fail.size() == 0){
            return new CommonResult(200,"账号注册成功",null);
        }

        Map<String,List<String>> map = new HashMap<>();
        map.put("注册成功账号",success);
        map.put("账密错误账号",passwordMistake);
        map.put("已经注册过的账号",exist);
        if(fail.size() == 0){
            return new CommonResult(444,"账密错误或已经注册过了",map);
        }else {
            map.put("fail",fail);
            return new CommonResult(555,"请通知后台管理员处理错误",map);
        }
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User getOne(User user) {
        return userMapper.getOne(user);
    }

    @Override
    public Integer delOne(User user) {
        return userMapper.delOne(user);
    }

    @Override
    public Integer restOne(User user) {
        return userMapper.restOne(user);
    }

    @Override
    public User getOneNoIsDel(User user) {
        return userMapper.getOneNoIsDel(user);
    }
}
