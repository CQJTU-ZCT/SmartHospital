package com.cqjtu.service;

import com.cqjtu.mapper.ExclusiveDoctorStatusMapper;
import com.cqjtu.model.ExclusiveDoctorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
@Service
public class ExclusiveDoctorStatusServiceImpl implements ExclusiveDoctorStatusService {
    @Autowired
    private ExclusiveDoctorStatusMapper statusMapper;

    @Override
    public List<ExclusiveDoctorStatus> getStatus() {
        return statusMapper.selectByExample(null);
    }
}
