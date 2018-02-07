package com.cqjtu.service;

import com.cqjtu.model.Nation;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
public interface NationService {

    /**
     * 获取民族信息
     * @return
     */
    List<Nation> getNations();
}
