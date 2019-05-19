package com.sojson.crs.service;

import com.sojson.common.model.Student;

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
}
