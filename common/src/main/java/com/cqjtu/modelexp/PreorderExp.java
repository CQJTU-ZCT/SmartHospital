package com.cqjtu.modelexp;

import com.cqjtu.model.Branch;
import com.cqjtu.model.Doctor;
import com.cqjtu.model.PreOrders;
import com.cqjtu.model.PreorderStatus;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-10 19:12
 **/

public class PreorderExp {
    private PreOrders preOrders;
    private Doctor doctor;
    private Branch branch;
    private PreorderStatus preorderStatus;

    public PreorderStatus getPreorderStatus() {
        return preorderStatus;
    }

    public void setPreorderStatus(PreorderStatus preorderStatus) {
        this.preorderStatus = preorderStatus;
    }

    public PreOrders getPreOrders() {
        return preOrders;
    }

    public void setPreOrders(PreOrders preOrders) {
        this.preOrders = preOrders;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
