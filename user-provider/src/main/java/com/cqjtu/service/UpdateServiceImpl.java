package com.cqjtu.service;

import com.cqjtu.mapperexp.UserMapperExp;
import com.cqjtu.model.Users;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

/**
 * @author Tangyu
 * @date 18/4/27
 */
@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UserMapperExp userMapperExp;

    @Override
    public int updateUserPhone(String idCard , String phone) {
        try {
            return userMapperExp.updateUserPhone(idCard,phone);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateUserMail(String idCard, String mail) {
        try {
            return userMapperExp.updateUserMail(idCard , mail);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateUserPassword(String idCard, String password) {
        try {
            return userMapperExp.updateUserPassword(idCard,password);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0;
        }
    }
}
