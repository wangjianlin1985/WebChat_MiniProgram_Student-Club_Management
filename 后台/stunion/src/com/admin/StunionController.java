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
@RequestMapping(value = "admin/stunion")
public class StunionController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		List<?> userIds = (List<?>) baseService.findListByHql(" from User where 1=1 ");
		request.setAttribute("userIds", userIds);
		return "admin/stunion/list";//跳转到WebContent/WEB-INF/views/stunion/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		List<?> userIds = (List<?>) baseService.findListByHql(" from User where 1=1 ");
		request.setAttribute("userIds", userIds);
		return "admin/stunion/table";//跳转到WebContent/WEB-INF/views/stunion/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		List<?> userIds = (List<?>) baseService.findListByHql(" from User where 1=1 ");
		request.setAttribute("userIds", userIds);
		return "admin/stunion/add";//跳转到WebContent/WEB-INF/views/stunion/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		Stunion model = (Stunion) baseService.getById(new Stunion(), id);
		request.setAttribute("model", model);
		List<?> userIds = (List<?>) baseService.findListByHql(" from User where 1=1 ");
		request.setAttribute("userIds", userIds);
		return "admin/stunion/edit";//跳转到WebContent/WEB-INF/views/stunion/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new Stunion().getClass().getName()+" where 1=1 ";
		//名称查询
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			hql += " and name like '%"+name+"%' ";
		}
		//部长查询
		String userId = request.getParameter("userId");
		if(!StringUtil.isEmpty(userId)){
			hql += " and userId in (select id from User where 1=1 and name like '%"+userId+"%') ";
		}
		//电话查询
		String tel = request.getParameter("tel");
		if(!StringUtil.isEmpty(tel)){
			hql += " and tel like '%"+tel+"%' ";
		}
		//口号查询
		String slogan = request.getParameter("slogan");
		if(!StringUtil.isEmpty(slogan)){
			hql += " and slogan like '%"+slogan+"%' ";
		}
		page = baseService.findPageList(hql, page);
		List<Stunion> list = (List<Stunion>) page.getData();
		for (Stunion stunion : list) {
			User m4 = (User) baseService.findObject(" from User where 1=1 and id = '"+stunion.getUserId()+"' ");
			if(m4 != null){
				stunion.setUserIdTxt(m4.getName());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,Stunion model){
		//判断是否已存在
		Stunion model2 = (Stunion) baseService.findObject(" from "+new Stunion().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		Stunion model = (Stunion) baseService.getById(new Stunion(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,Stunion model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,Stunion model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
