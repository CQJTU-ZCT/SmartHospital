package com.cqjtu.service;

import com.cqjtu.mapper.HospitalMapper;
import com.cqjtu.mapperexp.HospitalMapperExp;
import com.cqjtu.model.Hospital;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/25.
 */
@Service
public class HospitalServiceImpl implements HospitalService {


    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private HospitalMapperExp hospitalMapperExp;

    @Override
    public boolean registerHospital(Hospital hospital) {
        boolean result = false;
        try {
            if (hospital.getHospitalId() == null || hospital.getHospitalId().length() <=0){
                hospital.setHospitalId(UUID.randomUUID().toString().replaceAll("-",""));
            }
            int insert = hospitalMapper.insert(hospital);
            if (insert != 1){
                result = false;
            }else {
                result = true;
            }
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
        }
        return result;
    }

    @Override
    public Hospital getHospital() {
        return hospitalMapperExp.getHospital();
    }

    @Override
    public List<Hospital> getHospitalByName(String name) {
        return hospitalMapperExp.getHospitalByName(name);
    }

    @Override
    public List<Hospital> getHospitalByAddress(String address) {
        return hospitalMapperExp.getHospitalByAddress(address);
    }

    @Override
    public List<Hospital> getHospitalByTitu(BigDecimal longitude, BigDecimal latitude) {
        return null;
    }
}
