package com.cqjtu.mapperexp;

import com.cqjtu.model.Profile;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/4.
 */
public interface ProfileMapperExp {

    int insertProfile(Profile profile);


    int updateProfile(Profile profile);

    Profile queryProfile(Profile profile);

}
