package com.sojson.crs.service.impl;

import com.sojson.common.model.Student;
import com.sojson.crs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.sojson.common.dao.StudentMapper;
import org.springframework.stereotype.Service;

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



}
