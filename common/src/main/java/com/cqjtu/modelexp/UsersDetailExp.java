package com.cqjtu.modelexp;

import com.cqjtu.model.*;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-29 10:34
 **/

public class UsersDetailExp {

    private Users users;
    private AccountStatus accountStatus;
    private Role role;
    private UsersDetail usersDetail;
    private Nation nation;
    private Profile profile;
    private Photo photo;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UsersDetail getUsersDetail() {
        return usersDetail;
    }

    public void setUsersDetail(UsersDetail usersDetail) {
        this.usersDetail = usersDetail;
    }
}
