package com.cqjtu.service;

import com.cqjtu.mapperexp.ExclusiveDoctorMapperExp;
import com.cqjtu.model.ExclusiveDoctor;
import com.cqjtu.modelexp.ExclusiveDoctorExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
@Service
public class ExclusiveDoctorServiceImpl implements ExclusiveDoctorService {


    @Autowired
    private ExclusiveDoctorMapperExp exclusiveDoctorMapperExp;

    @Override
    public int addExclusiveDoctor(ExclusiveDoctor exclusiveDoctor) {
        return exclusiveDoctorMapperExp.addExclusiveDoctor(exclusiveDoctor);
    }

    @Override
    public int updateExclusiveDoctor(ExclusiveDoctor exclusiveDoctor) {
        return exclusiveDoctorMapperExp.updateExclusiveDoctor(exclusiveDoctor);
    }

    @Override
    public List<ExclusiveDoctorExp> queryExclusiveDoctor(ExclusiveDoctor exclusiveDoctor) {
        return exclusiveDoctorMapperExp.queryExclusiveDoctor(exclusiveDoctor);
    }
}
