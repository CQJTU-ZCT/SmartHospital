package com.cqjtu.mapperexp;

import com.cqjtu.model.Branch;
import com.cqjtu.model.Hospital;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc 医院扩展mapper
 * @date 2018/1/25.
 */
public interface HospitalMapperExp {
    /**
     * 获取所有的医院列表，
     * 可以通过分页插件优化
     * @param name
     * @param address
     * @return
     */
    List<Hospital> getHospitals(@Param("name") String name,@Param("address") String address);

    /**
     * 根据医院名称获取医院列表，
     * 可以通过分页插件优化
     * @param name
     * @return
     */
    List<Hospital> getHospitalByName(String name);

    /**
     *  根据医院地址获取医院列表，
     *  可以通过分页插件优化
     * @param address
     * @return
     */
    List<Hospital> getHospitalByAddress(String address);

    /**
     * 根据经纬度获取附近的医院列表，
     * 可以通过分页插件优化
     * @param longitude
     * @param latitude
     * @return
     */
    List<Hospital> getHospitalByTitu(BigDecimal longitude, BigDecimal latitude);



}
