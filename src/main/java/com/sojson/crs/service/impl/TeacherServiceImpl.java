package com.sojson.crs.service.impl;


import com.sojson.common.dao.TeacherMapper;

import com.sojson.common.model.Teacher;

import com.sojson.common.model.TeacherCourses;
import com.sojson.crs.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Allen
 */
@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public Teacher queryById(String id){
        return teacherMapper.queryById(id);
    }

    @Override
    public void updateById(String id, String openid, String role) {
        teacherMapper.updateById(id, openid);
    }

    @Override
    public List<TeacherCourses> queryByIdWeek(String tId, String week) {

        return teacherMapper.queryByIdWeek(tId, week);
    }
}
