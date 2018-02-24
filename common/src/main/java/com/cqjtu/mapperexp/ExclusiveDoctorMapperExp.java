package com.cqjtu.mapperexp;

import com.cqjtu.model.ExclusiveDoctor;
import com.cqjtu.modelexp.ExclusiveDoctorExp;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
public interface ExclusiveDoctorMapperExp {

    /**
     * 添加状态为申请的专属医生
     * @param exclusiveDoctor
     * @returnc
     */
    int addExclusiveDoctor(ExclusiveDoctor exclusiveDoctor);

    /**
     * 修改专属医生状态
     * @param exclusiveDoctor
     * @return
     */
    int updateExclusiveDoctor(ExclusiveDoctor exclusiveDoctor);

    /**
     * 查询专属医生
     * @param exclusiveDoctor
     * @return
     */
    List<ExclusiveDoctorExp> queryExclusiveDoctor(ExclusiveDoctor exclusiveDoctor);

}
