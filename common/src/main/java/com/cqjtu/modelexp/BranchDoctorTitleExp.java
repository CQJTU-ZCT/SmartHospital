package com.cqjtu.modelexp;

import com.cqjtu.model.Branch;
import com.cqjtu.model.Doctor;
import com.cqjtu.model.Title;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/5.
 */
public class BranchDoctorTitleExp {


    private Integer bdtId;
    private Branch branch;
    private Doctor doctor;
    private Title title;

    public Integer getBdtId() {
        return bdtId;
    }

    public void setBdtId(Integer bdtId) {
        this.bdtId = bdtId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
