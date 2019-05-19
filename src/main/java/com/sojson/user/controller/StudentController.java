package com.sojson.user.controller;

import com.sojson.common.model.Student;
import com.sojson.crs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Allen
 */

@Controller
@Scope(value="prototype")
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @RequestMapping(value = "queryById")
    @ResponseBody
    public Student queryById(ModelMap map, String id){
        Student student = studentService.queryById(id);
        System.out.println("进来啦！！！！！！");
        map.put("student", student);
        student.toString();
        return student;
    }
}
