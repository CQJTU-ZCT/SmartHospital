package com.cqjtu.service;

import com.cqjtu.mapper.UserMapper;


import com.cqjtu.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;


    @Override
    public Users findUserByUsername(String username) {
        return  mapper.findUserByUsername(username);
    }
}
