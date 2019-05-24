package com.sojson.crs.service.impl;

import com.sojson.common.model.Student;
import com.sojson.common.model.StudentCourses;
import com.sojson.crs.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import com.sojson.common.dao.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Allen
 */
@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentMapper studentMapper;
    @Override
    public Student queryById(String id){
        return studentMapper.queryById(id);
    }

    @Override
    public void updateById(String id, String openid, String role) {
        studentMapper.updateById(id, openid);
    }

    @Override
    public List<StudentCourses> queryByClassName(String className,String week){
        return  studentMapper.queryByClassName(className, week);
    }

    @Override
    public Student queryByOpenId(String openid){
        return studentMapper.queryByOpenId(openid);
    }
}
