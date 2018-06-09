package com.cqjtu.modelexp;

import com.cqjtu.model.*;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-08 18:10
 **/

public class SosRecordExp {

    private SosRecord sosRecord;

    private Users users;

    private UsersDetail usersDetail;

    private OkfaStatus okfaStatus;

    private Sex sex;



    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }



    public SosRecord getSosRecord() {
        return sosRecord;
    }

    public void setSosRecord(SosRecord sosRecord) {
        this.sosRecord = sosRecord;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public UsersDetail getUsersDetail() {
        return usersDetail;
    }

    public void setUsersDetail(UsersDetail usersDetail) {
        this.usersDetail = usersDetail;
    }

    public OkfaStatus getOkfaStatus() {
        return okfaStatus;
    }

    public void setOkfaStatus(OkfaStatus okfaStatus) {
        this.okfaStatus = okfaStatus;
    }
}
