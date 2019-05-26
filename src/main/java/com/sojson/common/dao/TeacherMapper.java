package com.sojson.common.dao;

import com.sojson.common.model.Student;
import com.sojson.common.model.Teacher;
import com.sojson.common.model.TeacherCourses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * @author Allen
 */
public interface TeacherMapper {
    /**
     * 根据老师职工号查询老师
     * @param id
     * @return 返回根据老师Id查询到的对象
     */
    Teacher queryById(@Param("id") String id);

    /**
     * 根据id更新该老师的微信号（openid）
     * @param id
     * @param openid
     */
    void updateById(@Param("id") String id, @Param("openid") String openid);

    List<TeacherCourses> queryByIdWeek(@Param(value = "tId")String tId, @Param(value = "week")String week);

    Teacher queryByOpenid(@Param(value = "openid")String openid);
    int createNewTable(@Param("tableName") String tableName);

    int dropTable(@Param("tableName") String tableName);

    void insertStudent(@Param(value = "sId")String sId, @Param(value = "sName")String sName, @Param(value = "className")String className, @Param(value = "isChecked")int isChecked, @Param(value = "tableName")String tableName);

    int queryStudents(@Param(value = "tableName") String tableName);

    List<Student> checkResult(@Param(value = "tableName")String tableName);

    void updateResign(@Param(value = "sId")String sId,@Param(value = "tableName")String tableName);

    int existTable(@Param(value = "tableName")String tableName);

    void updateParticiCnt(@Param(value = "tableName")String tableName);
}
