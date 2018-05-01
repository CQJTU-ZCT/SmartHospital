package com.cqjtu.service;

import com.cqjtu.modelexp.UsersExp;
import org.springframework.stereotype.Service;

/**
 * Created by Tangyu on 2018/4/20.
 */
@Service
public interface UserService {

    //根据用户idCard查询用户信息
    UsersExp getUserByIdCard(String idCard);


}
