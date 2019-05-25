package com.sojson.common.dao;

import com.sojson.common.model.Student;
import com.sojson.common.model.StudentCourses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * @author Allen
 */
public interface StudentMapper {
    /**
     * 根据学生学号查询学生
     * @param id
     * @return 返回根据学生Id查询到的对象
     */
    Student queryById(@Param("id") String id);

    /**
     * 根据学生微信号查询学生
     * @param openId
     * @return 返回根据学生Id查询到的对象
     */
    Student queryByOpenId(@Param("openId") String openId);

    /**
     * 根据id更新该学生的微信号（openid）
     * @param id
     * @param openid
     */
    void updateById(@Param("id") String id, @Param("openid") String openid);

    /**
     * 根据班级查询当天的课程
     * @param className 要查询的班级名称
     * @param week 要查询周几的课程
     * @return 查询到符合条件的所以记录
     */
    List<StudentCourses> queryByClassName(@Param("className")String className, @Param("week") String week);

    List<Student> queryByClassNameForT(@Param(value = "className")String className);


    void updateIsChecked(@Param(value = "tableName")String tableName, @Param(value = "sId")String sId);

}
