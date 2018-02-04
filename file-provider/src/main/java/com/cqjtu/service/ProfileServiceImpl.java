package com.cqjtu.service;


import com.cqjtu.mapperexp.ProfileMapperExp;
import com.cqjtu.model.Profile;
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
    private ProfileMapperExp profileMapperExp;

    @Override
    public int addProfile(Profile profile) {
        int result = 0;
        Profile profileSelect = getProfile(profile.getProfileId());
        if (profileSelect == null ){
            try {
                result = profileMapperExp.insertProfile(profile);
            }catch (Exception e){
                result = 0;
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            }
        }else {
            try {
                result = profileMapperExp.updateProfile(profile);
            }catch (Exception e){
                result = 0;
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public int updateProfile(Profile profile) {
        return profileMapperExp.updateProfile(profile);
    }

    @Override
    public Profile getProfile(String idCard) {
        Profile profile = new Profile();
        profile.setProfileId(idCard);
        return profileMapperExp.queryProfile(profile);
    }
}
