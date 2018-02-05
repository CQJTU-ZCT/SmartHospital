package com.cqjtu.mapperexp;


import com.cqjtu.model.Photo;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/4.
 */
public interface PhotoMapperExp {

    int insertPhoto(Photo photo);


    int updatePhoto(Photo photo);

    Photo queryPhoto(Photo photo);

}
