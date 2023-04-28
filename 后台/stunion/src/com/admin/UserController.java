package com.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.StringUtil;
import com.base.util.field.FieldUtil;
import com.base.util.page.PageUtils;
import com.model.*;

@Controller
@RequestMapping(value = "admin/user")
public class UserController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		List<?> roles = (List<?>) baseService.findListByHql(" from Dict where code = `user_role` ");
		request.setAttribute("roles", roles);
		return "admin/user/list";//跳转到WebContent/WEB-INF/views/user/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		List<?> roles = (List<?>) baseService.findListByHql(" from Dict where code = `user_role` ");
		request.setAttribute("roles", roles);
		return "admin/user/table";//跳转到WebContent/WEB-INF/views/user/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		List<?> roles = (List<?>) baseService.findListByHql(" from Dict where code = `user_role` ");
		request.setAttribute("roles", roles);
		return "admin/user/add";//跳转到WebContent/WEB-INF/views/user/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		User model = (User) baseService.getById(new User(), id);
		request.setAttribute("model", model);
		List<?> roles = (List<?>) baseService.findListByHql(" from Dict where code = `user_role` ");
		request.setAttribute("roles", roles);
		return "admin/user/edit";//跳转到WebContent/WEB-INF/views/user/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new User().getClass().getName()+" where 1=1 ";
		//姓名查询
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			hql += " and name like '%"+name+"%' ";
		}
		//账号查询
		String code = request.getParameter("code");
		if(!StringUtil.isEmpty(code)){
			hql += " and code like '%"+code+"%' ";
		}
		//角色查询
		String role = request.getParameter("role");
		if(!StringUtil.isEmpty(role)){
			hql += " and role in (select val from Dict where code = `user_role` and txt like '%"+role+"%') ";
		}
		//手机号查询
		String phone = request.getParameter("phone");
		if(!StringUtil.isEmpty(phone)){
			hql += " and phone like '%"+phone+"%' ";
		}
		page = baseService.findPageList(hql, page);
		List<User> list = (List<User>) page.getData();
		for (User user : list) {
			Dict m5 = (Dict) baseService.findObject(" from Dict where code = `user_role` and val = '"+user.getRole()+"' ");
			if(m5 != null){
				user.setRoleTxt(m5.getTxt());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,User model){
		//判断是否已存在
		User model2 = (User) baseService.findObject(" from "+new User().getClass().getName()+" where id = '"+model.getId()+"' ");
		if(model2!=null) {
			result = failResult("操作失败，主键已存在!");
		}else {
			FieldUtil.setCreatedAt(model);
			boolean flag = baseService.save(model);
			if(flag){
				result = successResult(model);
			}else{
				result = failResult("保存失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	
	// 根据id查询
	@RequestMapping(value = "show")
	public void show(HttpServletRequest request,HttpServletResponse response,int id){
		User model = (User) baseService.getById(new User(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,User model){
		boolean flag = baseService.merge(request,model);
		if(flag){
			result = successResult("");
		}else{
			result = failResult("修改失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
	// 删除方法
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,User model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
