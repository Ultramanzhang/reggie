package com.zhegu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhegu.reggie.entity.User;

public interface UserService extends IService<User> {
    void sendMsg(String phone, String subject, String context);
}
