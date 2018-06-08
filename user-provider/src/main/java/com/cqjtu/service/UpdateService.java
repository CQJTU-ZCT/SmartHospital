package com.cqjtu.service;

import com.cqjtu.model.Users;
import com.cqjtu.model.UsersDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author Tangyu
 * @date 2018/4/27
 */
@Service
public interface UpdateService {
    /**
     * 更新用户电话号码
     * @param idCard
     * @param phone
     * @return
     */
    int updateUserPhone(String idCard , String phone);

    /**
     * 更新用户邮箱
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



    boolean exitsNationId( int  nationId);


    boolean exitsSexId( int sexId);


    int updateUsersDetail(UsersDetail usersDetail);

}
