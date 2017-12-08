package com.cqjtu.service;

import com.cqjtu.domain.AbstractUser;
import com.cqjtu.domain.User;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/7.
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param yhm 用户名
     * @return
     */
    public AbstractUser getUserInfoByYhm(String yhm);

    /**
     * 根据身份证号码查找用户
     * @param sfzhm 身份证号码
     * @return
     */
    public AbstractUser getUserInfoBySfzhm(String sfzhm);

    /**
     * 根据手机号码查找用户
     * @param dhhm 电话号码
     * @return
     */
    public AbstractUser getUserInfoByDhhm(String dhhm);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public boolean addUser(AbstractUser user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean updateUser(AbstractUser user);


}
