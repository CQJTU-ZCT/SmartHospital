package com.cqjtu.modelexp;


import com.cqjtu.model.Doctor;
import com.cqjtu.model.ExclusiveDoctorStatus;
import com.cqjtu.model.Users;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/2/6.
 */
public class ExclusiveDoctorExp {
    private Doctor doctor;
    private Users users;

    private ExclusiveDoctorStatus status;


    private String exclusiveDoctorId;


    public ExclusiveDoctorStatus getStatus() {
        return status;
    }

    public void setStatus(ExclusiveDoctorStatus status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getExclusiveDoctorId() {
        return exclusiveDoctorId;
    }

    public void setExclusiveDoctorId(String exclusiveDoctorId) {
        this.exclusiveDoctorId = exclusiveDoctorId;
    }
}
