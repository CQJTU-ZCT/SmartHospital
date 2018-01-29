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
    private PositionMapperExp positionMapperExp;

    @Override
    public int addPosition(Position position) {
       return positionMapperExp.addPosition(position);
    }

    @Override
    public int updatePosition(Position position) {
       return positionMapperExp.updatePosition(position);
    }

    @Override
    public List<Position> queryPosition(String name) {
        return positionMapperExp.queryPosition(name);
    }
}
