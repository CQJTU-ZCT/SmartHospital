package com.cqjtu.service;


import com.cqjtu.mapperexp.PhotoMapperExp;
import com.cqjtu.mapperexp.ProfileMapperExp;
import com.cqjtu.model.Photo;
import com.cqjtu.model.Profile;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/23.
 */
@Service
public class PhotoServiceImpl implements PhotoService {


    @Autowired
    private PhotoMapperExp photoMapperExp;

    @Override
    public int addPhoto(Photo photo) {
        int result = 0;
        Photo photoSelect = getPhoto(photo.getPhotoId());
        if (photoSelect == null ){
            try {
                result = photoMapperExp.insertPhoto(photo);
            }catch (Exception e){
                result = 0;
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            }
        }else {
            try {
                result = photoMapperExp.updatePhoto(photo);
            }catch (Exception e){
                result = 0;
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public int updatePhoto(Photo photo) {
        return photoMapperExp.updatePhoto(photo);
    }

    @Override
    public Photo getPhoto(String idCard) {
        Photo photo = new Photo();
        photo.setPhotoId(idCard);
        return photoMapperExp.queryPhoto(photo);
    }
}
