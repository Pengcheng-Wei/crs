package com.sojson.user.controller;

import com.sojson.common.model.Student;
import com.sojson.common.model.StudentCourses;
import com.sojson.common.model.Teacher;
import com.sojson.common.model.TeacherCourses;
import com.sojson.crs.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Scope(value="prototype")
@RequestMapping(value = "teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "teacherQueryCourses")
    @ResponseBody
    public Map<String,Object> teacherQueryCourses(String id, String week, HttpServletRequest request){

        List<TeacherCourses> ls = teacherService.queryByIdWeek(id, week);
        Teacher teacher = teacherService.queryById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("sc", ls);
        return map;
    }
}
