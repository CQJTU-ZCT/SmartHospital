package com.cqjtu.mapperexp;

import com.cqjtu.model.Doctor;
import org.apache.ibatis.annotations.Param;

import javax.print.Doc;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/27.
 */
public interface DoctorMapperExp {

    /**
     * 添加医生
     * @param doctor
     * @return
     */
    int addDoctor(Doctor doctor);

    /**
     * 修改医生
     * @param doctor
     * @return
     */
    int updateDoctor(Doctor doctor);

    /**
     * 查询医生
     * @param doctor
     * @return
     */
    List<Doctor>  queryDoctor(Doctor doctor);

    /**
     * 修改医生的身份证号码
     * @param idCard
     * @return
     */
    int updateDoctorIdCard(@Param("idCard") String idCard);


    /**
     * 根据身份证号码查询医生信息
     * @param idCard
     * @return
     */
    Doctor queryDoctorByIdCard(@Param("idCard") String idCard);

}
