package com.cqjtu.service;

import com.cqjtu.mapperexp.DoctorMapperExp;
import com.cqjtu.model.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/27.
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorMapperExp doctorMapperExp;


    @Override
    public int addDoctor(Doctor doctor) {
        return doctorMapperExp.addDoctor(doctor);
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        return doctorMapperExp.updateDoctor(doctor);
    }

    @Override
    public List<Doctor> queryDoctor(Doctor doctor) {
        return doctorMapperExp.queryDoctor(doctor);
    }

    @Override
    public int updateDoctorIdCard(String idCard) {
        return doctorMapperExp.updateDoctorIdCard(idCard);
    }



    /**
     * 根据身份证号码查询医生信息
     * @param idCard
     * @return
     */
    @Override
    public Doctor queryDoctorByIdCard(@Param("idCard") String idCard){
        return doctorMapperExp.queryDoctorByIdCard(idCard);
    }

}
