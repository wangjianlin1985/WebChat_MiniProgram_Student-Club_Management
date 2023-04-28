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
@RequestMapping(value = "admin/student")
public class StudentController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		List<?> clazzIds = (List<?>) baseService.findListByHql(" from Clazz where 1=1 ");
		request.setAttribute("clazzIds", clazzIds);
		return "admin/student/list";//跳转到WebContent/WEB-INF/views/student/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		List<?> clazzIds = (List<?>) baseService.findListByHql(" from Clazz where 1=1 ");
		request.setAttribute("clazzIds", clazzIds);
		return "admin/student/table";//跳转到WebContent/WEB-INF/views/student/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		List<?> clazzIds = (List<?>) baseService.findListByHql(" from Clazz where 1=1 ");
		request.setAttribute("clazzIds", clazzIds);
		return "admin/student/add";//跳转到WebContent/WEB-INF/views/student/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		Student model = (Student) baseService.getById(new Student(), id);
		request.setAttribute("model", model);
		List<?> clazzIds = (List<?>) baseService.findListByHql(" from Clazz where 1=1 ");
		request.setAttribute("clazzIds", clazzIds);
		return "admin/student/edit";//跳转到WebContent/WEB-INF/views/student/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new Student().getClass().getName()+" where 1=1 ";
		//姓名查询
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			hql += " and name like '%"+name+"%' ";
		}
		//学号查询
		String sno = request.getParameter("sno");
		if(!StringUtil.isEmpty(sno)){
			hql += " and sno like '%"+sno+"%' ";
		}
		//班级查询
		String clazzId = request.getParameter("clazzId");
		if(!StringUtil.isEmpty(clazzId)){
			hql += " and clazzId in (select id from Clazz where 1=1 and name like '%"+clazzId+"%') ";
		}
		//手机号查询
		String phone = request.getParameter("phone");
		if(!StringUtil.isEmpty(phone)){
			hql += " and phone like '%"+phone+"%' ";
		}
		//身份证查询
		String idcard = request.getParameter("idcard");
		if(!StringUtil.isEmpty(idcard)){
			hql += " and idcard like '%"+idcard+"%' ";
		}
		//邮箱查询
		String email = request.getParameter("email");
		if(!StringUtil.isEmpty(email)){
			hql += " and email like '%"+email+"%' ";
		}
		page = baseService.findPageList(hql, page);
		List<Student> list = (List<Student>) page.getData();
		for (Student student : list) {
			Clazz m4 = (Clazz) baseService.findObject(" from Clazz where 1=1 and id = '"+student.getClazzId()+"' ");
			if(m4 != null){
				student.setClazzIdTxt(m4.getName());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,Student model){
		//判断是否已存在
		Student model2 = (Student) baseService.findObject(" from "+new Student().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		Student model = (Student) baseService.getById(new Student(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,Student model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,Student model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
