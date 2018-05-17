package com.cqjtu.service;




import com.cqjtu.model.Users;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */


public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public Users findUserByUsername(String username);
}
