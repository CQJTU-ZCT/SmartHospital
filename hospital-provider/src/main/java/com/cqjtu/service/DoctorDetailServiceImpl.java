package com.cqjtu.service;

import com.cqjtu.mapperexp.DoctorDetailMapperExp;
import com.cqjtu.model.DoctorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/27.
 */
@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    @Autowired
    private DoctorDetailMapperExp doctorDetailMapperExp;

    @Override
    public int addDoctorDetail(DoctorDetail doctorDetail) {
        return doctorDetailMapperExp.addDoctorDetail(doctorDetail);
    }

    @Override
    public int updateDoctorDetail(DoctorDetail doctorDetail) {
        return doctorDetailMapperExp.updateDoctorDetail(doctorDetail);
    }

    @Override
    public List<DoctorDetail> queryDoctorDetail(DoctorDetail doctorDetail) {
        return doctorDetailMapperExp.queryDoctorDetail(doctorDetail);
    }
}
