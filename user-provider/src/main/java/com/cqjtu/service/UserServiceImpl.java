package com.cqjtu.service;

import com.cqjtu.mapperexp.UserMapperExp;
import com.cqjtu.modelexp.UsersExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tangyu on 2018/4/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperExp userMapperExp;

    @Override
    public UsersExp getUserByIdCard(String idCard) {
        try{
            return userMapperExp.getUserByIdCard(idCard);
        }catch (Exception e){
            return null;
        }

    }
}
