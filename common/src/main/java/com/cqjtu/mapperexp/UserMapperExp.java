package com.cqjtu.mapperexp;

import com.cqjtu.model.Users;
import com.cqjtu.modelexp.UsersExp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 * @author Tangyu
 * @date 2018/4/11.
 */
public interface UserMapperExp {

    int insertUsers(Users users);

    UsersExp getUserByIdCard(String idCard);

    UsersExp getUserByPhone(String phoneNumber);

    List<UsersExp> getUserByRealname(String realName);

    List<UsersExp> getUserByAccountStatusId(Integer accountStatusId);

    List<UsersExp> getUserByRoleId(Integer roleId);

    UsersExp getUserByMail(String mail);

    /**
     * 修改用户电话号码
     * @param phone
     * @return
     */
    int updateUserPhone(String idCard , String phone);

    /**
     * 修改用户邮箱
     * @param idCard
     * @param mail
     * @return
     */
    int updateUserMail(String idCard , String mail);

    /**
     * 更新用户账户密码
     * @param idCard
     * @param password
     * @return
     */
    int updateUserPassword(String idCard , String password);
}
