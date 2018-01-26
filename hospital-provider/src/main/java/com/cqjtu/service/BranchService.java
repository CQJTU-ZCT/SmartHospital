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
     * @param hospitalId
     * @return
     */
    List<Branch> queryBranchesByName(String name,String hospitalId);

    /**
     * 根据名称和医院编号来获取科室信息
     * 名称为空则获取全部
     * @param introduction
     * @param hospitalId
     * @return
     */
    List<Branch> queryBranchesByIntroduction(String introduction,String hospitalId);

    /**
     * 根据医院编号查询科室
     * @param hospitalId
     * @return
     */
    List<Branch> queryBranches(String hospitalId);




}
