package com.cqjtu.service;

import com.cqjtu.mapper.PictureMapper;
import com.cqjtu.model.Picture;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/23.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public boolean addProfile(Picture picture) {
        boolean result = true;
        Picture profile = getProfile(picture.getPictureId());
        if (profile == null ){
            try {
                int insert = pictureMapper.insert(picture);
                if (insert != 1){
                    result = false;
                }
            }catch (Exception e){
                result = false;
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            }
        }else {
            try {
                int update = pictureMapper.updateByPrimaryKey(picture);
                if (update != 1){
                    result = false;
                }
            }catch (Exception e){
                result = false;
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public Picture getProfile(String idCard) {
        Picture picture = pictureMapper.selectByPrimaryKey(idCard);
        return picture;
    }
}
