package com.cqjtu.service;

import com.cqjtu.mapper.NationMapper;
import com.cqjtu.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
@Service
public class NationServiceImpl implements  NationService {
    @Autowired
    private NationMapper mapper;

    @Override
    public List<Nation> getNations() {
        return mapper.selectByExample(null);
    }
}
