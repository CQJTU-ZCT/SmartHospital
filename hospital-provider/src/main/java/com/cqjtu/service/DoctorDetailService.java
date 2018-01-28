package com.cqjtu.service;

import com.cqjtu.model.DoctorDetail;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/27.
 */
public interface DoctorDetailService {

    /**
     * 添加
     * @param doctorDetail
     * @return
     */
    int addDoctorDetail(DoctorDetail doctorDetail);

    /**
     * 修改
     * @param doctorDetail
     * @return
     */
    int updateDoctorDetail(DoctorDetail doctorDetail);

    /**
     * 查询
     * @param doctorDetail
     * @return
     */
    List<DoctorDetail> queryDoctorDetail(DoctorDetail doctorDetail);
}
