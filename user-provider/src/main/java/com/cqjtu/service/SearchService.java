package com.cqjtu.service;

import com.cqjtu.model.Users;
import com.cqjtu.modelexp.UsersDetailExp;
import com.cqjtu.modelexp.UsersExp;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户查询接口
 * @author Tangyu
 * @date 2018/4/21.
 */
@Service
public interface SearchService {
    //按照用户idCard查询
    UsersExp selectUsersByIdCard(String idCard);

    //根据用户phone查询
    UsersExp selectUsersByPhone(String phoneNumber);

    //根据用户realname查询
    List<UsersExp> selectUsersByRealname(String realName);

    //根据用户accountStatusId查找
    List<UsersExp> selectUsersByAccountStatusId(Integer accountStatusId);

    //根据用户roleId查询
    List<UsersExp> selectUsersByRoleId(Integer roleId);

    //按照mail查找
    UsersExp selectUsersByMail(String mail);



    UsersDetailExp selectUsersDetailExpByIdCard(String idCard);

}
