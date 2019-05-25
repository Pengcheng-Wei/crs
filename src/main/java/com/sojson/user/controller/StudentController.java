package com.sojson.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sojson.common.model.Student;
import com.sojson.common.model.StudentCourses;
import com.sojson.common.utils.HttpRequestUtil;
import com.sojson.crs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Allen
 */

@Controller
@Scope(value="prototype")
@RequestMapping(value = "student")
public class StudentController {
    final String role_student = "student";
    @Autowired
    StudentService studentService;


    @RequestMapping(value = "studentQueryCourses")
    @ResponseBody
    public Map<String,Object> studentQueryCourses(String id, String week, HttpServletRequest request){

        System.out.println(request.getSession().getId());
        Student student = studentService.queryById(id);
        List<StudentCourses> ls = studentService.queryByClassName(student.getClassName(), week);
        Map<String, Object> map = new HashMap<>();
        map.put("student", student);
        map.put("sc", ls);
        return map;
    }

    @RequestMapping(value = "testController")
    @ResponseBody
    public Map<String,Object> testController(String js_code, HttpServletRequest request){
        final String appid = "wx15764070fee38012";
        final String secret = "3208ddfe27dab73e3504a5ccfddf1222";
        final String grant_type = "authorization_code";
        final String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret=" + secret + "&js_code=" + js_code +"&grant_type="+ grant_type;
        Map<String, Object> map = new HashMap<>();
        String reslut = HttpRequestUtil.get(url);

        JSONObject jsonObject = JSON.parseObject(reslut);
        String openid = jsonObject.getString("openid");
        System.out.println("openid = " + openid);

        Student stu = studentService.queryByOpenId(openid);
        if(stu != null){
            map.put("bind", "ture");
            map.put("student", stu);
        }
        else{
            map.put("bind", "false");
        }

        map.put("openid", openid);
        map.put("sessionId", request.getSession().getId());

        return map;
    }
    @RequestMapping(value = "checkRandomNum")
    @ResponseBody
    public Map<String,Object> checkRandomNum(String tId, String sId, int randomNum){
        Map<String, Object> map = new HashMap<>();
        boolean result = studentService.updateIsChecked("t"+tId, sId, randomNum);
        map.put("result", result);
        return map;
    }





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
