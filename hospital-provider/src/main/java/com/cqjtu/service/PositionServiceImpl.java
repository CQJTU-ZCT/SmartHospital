package com.cqjtu.service;

import com.cqjtu.mapper.PositionMapper;
import com.cqjtu.mapperexp.PositionMapperExp;
import com.cqjtu.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
@Service
public class PositionServiceImpl implements PositionService {


    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private PositionMapperExp positionMapperExp;

    @Override
    public boolean addPosition(Position position) {
        boolean result = false;
        if (positionMapper.insert(position) == 1){
            result = true;
        }
        return result;
    }

    @Override
    public boolean updatePosition(Position position) {
        boolean result = false;
        if (positionMapperExp.updatePosition(position) == 1){
            result = true;
        }
        return result;
    }

    @Override
    public List<Position> queryPosition(String name) {
        return positionMapperExp.queryPosition(name);
    }
}
