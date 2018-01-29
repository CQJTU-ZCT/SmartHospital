package com.cqjtu.service;

import com.cqjtu.mapperexp.TitleMapperExp;
import com.cqjtu.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/26.
 */
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleMapperExp titleMapperExp;

    @Override
    public int addTitle(Title title) {
        return titleMapperExp.addTitle(title);
    }

    @Override
    public int updateTitle(Title title) {
        return titleMapperExp.updateTitle(title);
    }

    @Override
    public List<Title> queryTitle(String name) {
        return titleMapperExp.queryTitle(name);
    }
}
