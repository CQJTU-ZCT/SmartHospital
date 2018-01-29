package com.cqjtu.mapperexp;

import com.cqjtu.model.BranchDoctorPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface BranchDoctorPositionMapperExp {

    /**
     * 添加科室-医生-职位的关系
     * @param branchDoctorPosition
     * @return
     */
    int addBranchDoctorPosition(BranchDoctorPosition branchDoctorPosition);


    /**
     * 修改科室-医生-职位的关系
     * @param branchDoctorPosition
     * @return
     */
    int updateBranchDoctorPosition(BranchDoctorPosition branchDoctorPosition);


    List<BranchDoctorPosition> queryBranchDoctorPosition(BranchDoctorPosition branchDoctorPosition);

}
