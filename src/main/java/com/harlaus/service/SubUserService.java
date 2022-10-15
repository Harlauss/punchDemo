package com.harlaus.service;

import com.harlaus.entity.SubUser;
import com.harlaus.entity.User;

public interface SubUserService {
    public String doRegister(SubUser subUser, User user);
}
