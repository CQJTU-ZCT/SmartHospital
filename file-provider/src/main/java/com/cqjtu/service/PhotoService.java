package com.cqjtu.service;


import com.cqjtu.model.Photo;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/23.
 */
public interface PhotoService {

    /**
     * 添加照片
     * @param photo
     * @return
     */
    int addPhoto(Photo photo);

    /**
     * 修改照片
     * @param photo
     * @return
     */
    int updatePhoto(Photo photo);

    /**
     * 获取照片
     * @param idCard
     * @return
     */
    Photo getPhoto(String idCard);
}
