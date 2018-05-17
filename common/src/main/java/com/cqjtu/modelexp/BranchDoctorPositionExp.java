package com.cqjtu.modelexp;

import com.cqjtu.model.Branch;
import com.cqjtu.model.Doctor;
import com.cqjtu.model.Position;
import com.cqjtu.model.Title;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/5.
 */
public class BranchDoctorPositionExp {


    private Integer bdpId;
    private Branch branch;
    private Doctor doctor;
    private Position position;


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

    public Integer getBdpId() {
        return bdpId;
    }

    public void setBdpId(Integer bdpId) {
        this.bdpId = bdpId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
