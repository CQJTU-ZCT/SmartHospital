package com.cqjtu.service;

import com.cqjtu.model.SosRecord;

import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-09 11:03
 **/
public interface SosService {

    int insert(SosRecord sosRecord);


    int finishSos(String sosId);


    int cancelSos();


    List<SosRecord> select(SosRecord sosRecord);


}
