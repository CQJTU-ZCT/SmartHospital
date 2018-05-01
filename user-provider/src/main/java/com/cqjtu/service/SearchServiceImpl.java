package com.cqjtu.service;

import com.cqjtu.mapperexp.UserMapperExp;
import com.cqjtu.model.Users;
import com.cqjtu.modelexp.UsersExp;
import com.cqjtu.tools.LoggerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户查询接口实现
 *
 * @author Tangyu
 * @date 2018/4/21.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserMapperExp userMapperExp;

    @Override
    public UsersExp selectUsersByIdCard(String idCard) {
        try {
            return userMapperExp.getUserByIdCard(idCard);
        } catch (Exception e) {
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }

    @Override
    public UsersExp selectUsersByPhone(String phoneNumber) {
        try {
            return userMapperExp.getUserByPhone(phoneNumber);
        } catch (Exception e) {
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<UsersExp> selectUsersByRealname(String realName) {
        try {
            return userMapperExp.getUserByRealname(realName);
        } catch (Exception e) {
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<UsersExp> selectUsersByAccountStatusId(Integer accountStatusId) {
        try {
            return userMapperExp.getUserByAccountStatusId(accountStatusId);
        } catch (Exception e) {
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<UsersExp> selectUsersByRoleId(Integer roleId) {
        try {
            return userMapperExp.getUserByRoleId(roleId);
        }catch (Exception e){
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }

    @Override
    public UsersExp selectUsersByMail(String mail) {
        try {
            return userMapperExp.getUserByMail(mail);
        } catch (Exception e) {
            LoggerTool.getLogger(this.getClass()).info(e.getMessage());
            return null;
        }
    }
}
