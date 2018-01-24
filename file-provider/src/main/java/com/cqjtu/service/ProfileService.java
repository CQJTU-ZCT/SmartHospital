package com.cqjtu.service;

import com.cqjtu.model.Picture;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/23.
 */
public interface ProfileService {

    boolean addProfile(Picture picture);

    Picture getProfile(String idCard);
}
