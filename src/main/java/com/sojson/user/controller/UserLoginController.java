package com.sojson.user.controller;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Student;
import com.sojson.common.model.Teacher;
import com.sojson.common.model.UUser;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.common.utils.StringUtils;
import com.sojson.common.utils.VerifyCodeUtils;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.crs.service.TeacherService;
import com.sojson.user.manager.UserManager;
import com.sojson.user.service.UUserService;
import net.sf.json.JSONObject;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sojson.crs.service.StudentService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * 
 * 用户登录相关，不需要做登录限制
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月3日 　<br/>
 * <p>
 * *******
 * <p>
 * @author zhou-baicheng
 * @email  i@itboy.net
 * @version 1.0,2016年5月3日 <br/>
 * 
 */
@Controller
@Scope(value="prototype")
@RequestMapping("u")
public class UserLoginController extends BaseController {

	@Resource
	UUserService userService;

	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherService;
	final String role_student = "student";
	final String role_teacher = "teacher";
	/**
	 * 登录跳转
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView login(){
		
		return new ModelAndView("user/login");
	}
	/**
	 * 注册跳转
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public ModelAndView register(){
		
		return new ModelAndView("user/register");
	}
	/**
	 * 注册 && 登录
	 * @param vcode		验证码	
	 * @param entity	UUser实体
	 * @return
	 */
	@RequestMapping(value="subRegister",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> subRegister(String vcode,UUser entity){
		resultMap.put("status", 400);
		if(!VerifyCodeUtils.verifyCode(vcode)){
			resultMap.put("message", "验证码不正确！");
			return resultMap;
		}
		String email =  entity.getEmail();
		
		UUser user = userService.findUserByEmail(email);
		if(null != user){
			resultMap.put("message", "帐号|Email已经存在！");
			return resultMap;
		}
		Date date = new Date();
		entity.setCreateTime(date);
		entity.setLastLoginTime(date);
		//把密码md5
		entity = UserManager.md5Pswd(entity);
		//设置有效
		entity.setStatus(UUser._1);
		
		entity = userService.insert(entity);
		LoggerUtils.fmtDebug(getClass(), "注册插入完毕！", JSONObject.fromObject(entity).toString());
		entity = TokenManager.login(entity, Boolean.TRUE);
		LoggerUtils.fmtDebug(getClass(), "注册后，登录完毕！", JSONObject.fromObject(entity).toString());
		resultMap.put("message", "注册成功！");
		resultMap.put("status", 200);
		return resultMap;
	}
	/**
	 * 登录提交
	 * @param entity		登录的UUser
	 * @param rememberMe	是否记住
	 * @param request		request，用来取登录之前Url地址，用来登录后跳转到没有登录之前的页面。
	 * @return
	 */
	@RequestMapping(value="submitLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitLogin(UUser entity,Boolean rememberMe,HttpServletRequest request, String role, String openid){
		Student student;
		Teacher teacher;
		if(role != null){
			if (role.equals(role_student) ){
				student = studentService.queryById(entity.getEmail());
				if (student == null) {
					resultMap.put("isExisted", false);
					return resultMap;
				}

			}
			else if (role.equals(role_teacher)){
				teacher = teacherService.queryById(entity.getEmail());
				if (teacher == null) {
					resultMap.put("isExisted", false);
					return resultMap;
				}
			}
		}

		try {
			entity = TokenManager.login(entity, rememberMe);


			resultMap.put("isExisted", true);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");

			resultMap.put("sessionId", request.getSession().getId());

			/**
			 * 实现登录成功后的与微信号绑定，即更新数据库的wechatid字段
			 */
			if(role!=null && openid != null){
                if (role.equals(role_student) ){

                    studentService.updateById(entity.getEmail(), openid, role);
                }
                if (role.equals(role_teacher)){
                    teacherService.updateById(entity.getEmail(), openid, role);
                }
            }

			/**
			 * shiro 获取登录之前的地址
			 * 之前0.1版本这个没判断空。
			 */
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			String url = null ;
			if(null != savedRequest){
				url = savedRequest.getRequestUrl();
			}
			/**
			 * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的
			 * String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
			 */
			LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]",url);
			//如果登录之前没有地址，那么就跳转到首页。
			if(StringUtils.isBlank(url)){
				url = request.getContextPath() + "/user/index.shtml";
			}
			//跳转地址
			resultMap.put("back_url", url);
		/**
		 * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
		 */
		} catch (DisabledAccountException e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号已经禁用。");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号或密码错误");
		}
			
		return resultMap;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(){
		try {
			TokenManager.logout();
			resultMap.put("status", 200);
		} catch (Exception e) {
			resultMap.put("status", 500);
			logger.error("errorMessage:" + e.getMessage());
			LoggerUtils.fmtError(getClass(), e, "退出出现错误，%s。", e.getMessage());
		}
		return resultMap;
	}
}
