package com.cqjtu.mapperexp;

import com.cqjtu.model.SosRecord;

import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-08 18:00
 **/
public interface OneKeyFirstAidMapperExp {


    int insert(SosRecord sosRecord);


    int finishSos(String sosId);


    int cancelSos();


    List<SosRecord> select(SosRecord sosRecord);


}
