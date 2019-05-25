package com.sojson.crs.service.impl;


import com.sojson.common.dao.StudentMapper;
import com.sojson.common.dao.TeacherMapper;

import com.sojson.common.model.Student;
import com.sojson.common.model.Teacher;

import com.sojson.common.model.TeacherCourses;
import com.sojson.crs.service.TeacherService;
import com.sojson.user.controller.TeacherController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Allen
 */
@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    StudentMapper studentMapper;
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

    @Override
    public int calling(String tId, String className) {
        String[] classNames = className.split("、");
        int cnt;
        List<Student> students = new ArrayList<>();
        String tableName = "t"+tId;
        teacherMapper.createNewTable(tableName);

        for (int i = 0; i < classNames.length; i++) {
            students.addAll(studentMapper.queryByClassNameForT(classNames[i]));
        }
        cnt = students.size();
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            teacherMapper.insertStudent(s.getsId(), s.getsName(), s.getClassName(), 0, tableName);
        }
        System.out.println(TeacherController.randomNum);
        return cnt;
    }

    @Override
    public int queryStudents(String tId) {
        String tableName = "t" + tId;
        return teacherMapper.queryStudents(tableName);
    }
}
