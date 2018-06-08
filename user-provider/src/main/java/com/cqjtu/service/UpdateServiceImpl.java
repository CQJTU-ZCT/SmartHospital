package com.cqjtu.service;

import com.cqjtu.mapper.NationMapper;
import com.cqjtu.mapper.SexMapper;
import com.cqjtu.mapperexp.UserMapperExp;
import com.cqjtu.model.Nation;
import com.cqjtu.model.Sex;
import com.cqjtu.model.Users;
import com.cqjtu.model.UsersDetail;
import com.cqjtu.tools.LoggerTool;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
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


    @Autowired
    private NationMapper nationMapper;

    @Autowired
    private SexMapper sexMapper;

    @Override
    public boolean exitsNationId(int nationId) {
        Nation nation = nationMapper.selectByPrimaryKey(nationId);
        if (nation == null){
            return  false;
        }else {
            return true;
        }
    }

    @Override
    public boolean exitsSexId(int sexId) {
        Sex sex = sexMapper.selectByPrimaryKey(sexId);
        if (sex == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int updateUsersDetail(UsersDetail usersDetail) {
        try {
            return  userMapperExp.updateUsersDetail(usersDetail);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return 0 ;
        }
    }


}
