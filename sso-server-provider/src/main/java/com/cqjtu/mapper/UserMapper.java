package com.cqjtu.mapper;


import com.cqjtu.domain.User;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findUserByUsername(String username);
}
