package com.sojson.common.dao;

import com.sojson.common.model.Student;
import org.apache.ibatis.annotations.Param;

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
}
