package com.cqjtu.mapperexp;

import com.cqjtu.model.Branch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface BranchMapperExp {
    /**
     * 根据名称和医院编号来获取科室信息
     * 名称为空则获取全部
     * @param name
     * @param hospitalId
     * @return
     */
    List<Branch> queryBranchesByName(@Param("name") String name, @Param("hospitalId") String hospitalId);


    /**
     * 根据介绍查询具有该介绍的科室
     * @param introduction
     * @param hospitalId
     * @return
     */
    List<Branch> queryBranchesByIntroduction(@Param("introduction") String introduction,@Param("hospitalId") String hospitalId);


    /**
     * 查询科室
     * @param hospitalId
     * @return
     */
    List<Branch> queryBranches(@Param("hospitalId") String hospitalId);


    /**
     * 根据主键选择性更新
     * @param branch
     * @return
     */
    int updateBranchByPrimaryKey(Branch branch);

}
