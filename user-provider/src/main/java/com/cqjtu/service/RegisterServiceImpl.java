package com.cqjtu.service;

import com.cqjtu.mapperexp.UserMapperExp;
import com.cqjtu.model.Users;
import com.cqjtu.modelexp.UsersExp;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tangyu on 2018/4/21.
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapperExp userMapperExp;

    @Override
    public int addUsers(Users users) {
         try {
             return userMapperExp.insertUsers(users);
         }catch (Exception e){
             LoggerTool.getLogger(this.getClass()).error(e.getMessage());
             return 0;
         }
    }
}
