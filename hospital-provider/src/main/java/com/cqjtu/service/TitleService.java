package com.cqjtu.service;

import com.cqjtu.model.Title;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface TitleService {

    /**
     * 添加职称信息
     * @param title
     * @return
     */
    int addTitle(Title title);

    /**
     * 修改职称信息
     * @param title
     * @return
     */
    int updateTitle(Title title);

    /**
     * 根据名称模糊查询
     * @param title
     * @return
     */
    List<Title> queryTitle(Title title);
}
