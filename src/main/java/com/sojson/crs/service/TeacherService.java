package com.sojson.crs.service;


import com.sojson.common.model.Teacher;

/***
 * @author Allen
 */
public interface TeacherService {
    /**
     * 服务层用于根据学号查询学生
     * @param id
     * @return 根据学号查询到的学生对象
     */
    Teacher queryById(String id);

    /**
     * 负责完成对应师生角色的更新
     * @param id
     * @param openid
     * @param role
     */
    void updateById(String id, String openid, String role);
}
