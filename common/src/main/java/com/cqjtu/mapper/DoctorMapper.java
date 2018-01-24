/**
 * DoctorMapper.class
 * Created in Intelij IDEA
 * <p>
 * Write Some Describe of this class here
 *
 * @author Mevur
 * @date 01/24/18 16:50
 */
package com.cqjtu.mapper;

import com.cqjtu.domain.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DoctorMapper {

    /**
     * 查询医生
     * @return 返回所有的医生数据
     */
    @Select("SELECT * FROM doctor")
    @Results({
            @Result(column = "id_card", property = "idCard", javaType = String.class),
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "phone", property = "phone", javaType = String.class),
            @Result(column = "password", property = "password", javaType = String.class),
            @Result(column = "mail", property = "mail", javaType = String.class)
    })
    List<Doctor> getAllDoctors();

    /**
     * 往数据库插入医生
     * @param doctor 插入的医生数据
     * @return 插入的医生数据
     */
    @Insert("INSERT INTO doctor(id_card, name, phone, password, mail) VALUES(#{idCard}," +
            "#{name}, #{phone}, #{password}, #{mail}")
    Doctor insert(Doctor doctor);

    /**
     * 更新医生数据
     * @param doctor 要更新的医生
     * @return 更新后的医生数据
     */
    @Update("UPDATE doctor SET name = #{name}, phone = #{phone}, password = #{password}," +
            "mail = #{mail} WHERE id_card = #{idCard}")
    Doctor update(Doctor doctor);

    /**
     * 删除医生数据
     * @param idCard 医生的身份证编号
     * @return 删除的医生信息
     */
    @Delete("DELETE FROM doctor WHERE id_card = #{idCard}")
    Doctor delete(Integer idCard);




}
