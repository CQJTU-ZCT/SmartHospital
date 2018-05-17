package com.cqjtu.service;

import com.cqjtu.model.Users;
import org.springframework.stereotype.Service;

/**
 * 用户注册接口
 * Created by Tangyu on 2018/4/21.
 */
@Service
public interface RegisterService {
    //添加用户
    int addUsers(Users users);
}
