package com.cqjtu.service;


import com.cqjtu.model.Profile;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/23.
 */
public interface ProfileService {

    /**
     * 添加头像
     * @param profile
     * @return
     */
    int addProfile(Profile profile);

    /**
     * 修改头像
     * @param profile
     * @return
     */
    int updateProfile(Profile profile);

    /**
     * 获取头像
     * @param idCard
     * @return
     */
    Profile getProfile(String idCard);
}
