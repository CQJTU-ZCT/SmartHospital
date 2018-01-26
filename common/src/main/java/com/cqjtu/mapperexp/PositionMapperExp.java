package com.cqjtu.mapperexp;

import com.cqjtu.model.Position;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableServer.POA;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface PositionMapperExp {

    /**
     * 查询名称
     * @param name
     * @return
     */
    List<Position> queryPosition(@Param("name") String name);

    /**
     * 修改职位信息
     * @param position
     * @return
     */
    int updatePosition(Position position);
}
