package com.sojson.crs.service;

import com.sojson.common.model.Student;
import com.sojson.common.model.StudentCourses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * @author Allen
 */
public interface StudentService {
    /**
     * 服务层用于根据学号查询学生
     * @param id
     * @return 根据学号查询到的学生对象
     */
    Student queryById(String id);

    /**
     * 负责完成对应师生角色的更新
     * @param id
     * @param openid
     * @param role
     */
    void updateById(String id, String openid, String role);
    /**
     * 根据班级查询当天的课程
     * @param className 要查询的班级名称
     * @param week 要查询周几的课程
     * @return 查询到符合条件的所以记录
     */
    List<StudentCourses> queryByClassName(String className, String week);

    /**
     * 服务层用于根据微信号查询学生
     * @param openid
     * @return
     */
    Student queryByOpenId(String openid);
    boolean updateIsChecked(String tableName, String sId,int randomNum);
}
