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
}
