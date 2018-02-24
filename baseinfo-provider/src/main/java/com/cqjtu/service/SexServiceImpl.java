package com.cqjtu.service;

import com.cqjtu.mapper.SexMapper;
import com.cqjtu.model.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
@Service
public class SexServiceImpl  implements  SexService{

    @Autowired
    private SexMapper sexMapper;

    @Override
    public List<Sex> getSexs() {
        return sexMapper.selectByExample(null);
    }
}
