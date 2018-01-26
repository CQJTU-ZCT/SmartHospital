package com.cqjtu.service;

import com.cqjtu.model.Branch;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface BranchService {

    /**
     * 添加科室
     * @param branch
     * @return
     */
    boolean addBranch(Branch branch);

    /**
     * 修改科室
     * @param branch
     * @return
     */
    boolean updateBranch(Branch branch);


    /**
     * 根据名称和医院编号来获取科室信息
     * 名称为空则获取全部
     * @param name
     * @return
     */
    List<Branch> queryBranchesByName(String name);

    /**
     * 根据名称和医院编号来获取科室信息
     * 名称为空则获取全部
     * @param introduction
     * @return
     */
    List<Branch> queryBranchesByIntroduction(String introduction);

    /**
     * 根据医院编号查询科室
     * @param name
     * @param introduction
     * @return
     */
    List<Branch> queryBranches(String introduction,String name);




}
