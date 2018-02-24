package com.cqjtu.service;

import com.cqjtu.model.Sex;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
public interface SexService {

    /**
     * 获取性别信息
     * @return
     */
    List<Sex> getSexs();

}
