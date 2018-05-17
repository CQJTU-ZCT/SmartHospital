package com.cqjtu.modelexp;

import com.cqjtu.model.AccountStatus;
import com.cqjtu.model.Role;
import com.cqjtu.model.Users;

/**
 * @author Tangyu
 * @date 2018/4/20.
 */
public class UsersExp     {
    private Users users;
    private AccountStatus accountStatus;
    private Role role;

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
}
