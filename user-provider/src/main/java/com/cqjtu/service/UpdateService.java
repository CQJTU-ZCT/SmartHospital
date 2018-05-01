package com.cqjtu.service;

import com.cqjtu.model.Users;
import org.springframework.stereotype.Service;

/**
 * @author Tangyu
 * @date 2018/4/27
 */
@Service
public interface UpdateService {
    /**
     * 更新用户电话号码
     * @param phone
     * @return
     */
    int updateUserPhone(String phone);


    /**
     *
     * @param users
     * @return
     */
    int updateUsers(Users users);
}
