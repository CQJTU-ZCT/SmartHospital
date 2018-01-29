package com.cqjtu.mapperexp;

import com.cqjtu.model.BranchDoctorTitle;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface BranchDoctorTitleMapperExp {

    /**
     * 添加科室 医生 职称
     * @param branchDoctorTitle
     * @return
     */
    int addBranchDoctorTitile(BranchDoctorTitle branchDoctorTitle);

    /**
     *  添加科室 医生 职称
     * @param branchDoctorTitle
     * @return
     */
    int updateBranchDoctorTitle(BranchDoctorTitle branchDoctorTitle);


    /**
     * 根据条件查询 科室 医生 职称
     * @param branchDoctorTitle
     * @return
     */
    List<BranchDoctorTitle> queryBranchDoctorTitle(BranchDoctorTitle branchDoctorTitle);

}
