package com.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.StringUtil;
import com.model.User;

@Controller
@RequestMapping(value = "admin")
public class AdminController extends BaseController {

	// 后台首页
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request, HttpServletResponse response, String page) {
		return "admin/index";
	}
	
	// 登录页面
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("User");
		return "admin/login";
	}
	
	// 退出登录
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("User");
		return "admin/login";
	}
		
	// 修改密码
	@RequestMapping(value = "pass")
	public String pass(HttpServletRequest request, HttpServletResponse response) {
		return "admin/pass";
	}

	// 登录方法
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "vlogin")
	public void vlogin(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		if (StringUtil.isEmpty(password) || StringUtil.isEmpty(code)) {
			result = failResult("账号密码不能为空!");
		} else {
			String hql = " from User where code = '" + code + "' and password = '" + password + "' ";
			List<User> list = (List<User>) baseService.findListByHql(hql);
			if (list != null && list.size() == 1) {
				User user = list.get(0);
				request.getSession().setAttribute("User", user);
				result = successResult("");
			} else {
				result = failResult("账号密码不匹配!");
			}
		}
		outJson(response, result);
	}

	//修改密码
	@RequestMapping(value = "vpwd")
	public void vpwd(HttpServletRequest request, HttpServletResponse response) {
		String opwd = request.getParameter("opwd");
		String npwd = request.getParameter("npwd");
		User user = (User) request.getSession().getAttribute("user");
		if (user.getPassword().equals(opwd)) {
			user.setPassword(npwd);
			baseService.update(user);
			result = successResult("");
		} else {
			result = failResult("原密码输入有误,请重新输入!");
		}
		outJson(response, result);
	}

}
