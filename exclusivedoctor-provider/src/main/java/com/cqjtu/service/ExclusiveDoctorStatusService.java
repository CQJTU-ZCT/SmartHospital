package com.cqjtu.service;

import com.cqjtu.model.ExclusiveDoctorStatus;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
public interface ExclusiveDoctorStatusService {

    /**
     * 获取专属医生状态
     * @return
     */
    List<ExclusiveDoctorStatus> getStatus();

}
