package com.cqjtu.mapperexp;

import com.cqjtu.model.Title;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
public interface TitleMapperExp {

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
     * 查询职称
     * @param name
     * @return
     */
    List<Title> queryTitle(Title title);

}
