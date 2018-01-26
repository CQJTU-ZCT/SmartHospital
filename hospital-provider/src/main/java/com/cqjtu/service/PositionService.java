package com.cqjtu.service;

import com.cqjtu.model.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface PositionService {

    /**
     * 添加职位信息
     * @param position
     * @return
     */
    boolean addPosition(Position position);

    /**
     * 修改职位信息
     * @param position
     * @return
     */
    boolean updatePosition(Position position);

    /**
     * 查询职位
     * @param name
     * @return
     */
    List<Position> queryPosition(@Param("name") String name);

}
