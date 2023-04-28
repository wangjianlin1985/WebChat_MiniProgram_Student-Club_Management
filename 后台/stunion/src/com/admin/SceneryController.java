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
@RequestMapping(value = "admin/scenery")
public class SceneryController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		String hql = " from Stunion where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and userId = '"+getSessionUser(request).getId()+"' ";
		}
		List<?> stunionIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("stunionIds", stunionIds);
		return "admin/scenery/list";//跳转到WebContent/WEB-INF/views/scenery/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		String hql = " from Stunion where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and userId = '"+getSessionUser(request).getId()+"' ";
		}
		List<?> stunionIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("stunionIds", stunionIds);
		return "admin/scenery/table";//跳转到WebContent/WEB-INF/views/scenery/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		String hql = " from Stunion where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and userId = '"+getSessionUser(request).getId()+"' ";
		}
		List<?> stunionIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("stunionIds", stunionIds);
		return "admin/scenery/add";//跳转到WebContent/WEB-INF/views/scenery/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		Scenery model = (Scenery) baseService.getById(new Scenery(), id);
		request.setAttribute("model", model);
		String hql = " from Stunion where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and userId = '"+getSessionUser(request).getId()+"' ";
		}
		List<?> stunionIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("stunionIds", stunionIds);
		return "admin/scenery/edit";//跳转到WebContent/WEB-INF/views/scenery/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new Scenery().getClass().getName()+" where 1=1 ";
		//名称查询
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			hql += " and name like '%"+name+"%' ";
		}
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"') ";
		}
		page = baseService.findPageList(hql, page);
		List<Scenery> list = (List<Scenery>) page.getData();
		for (Scenery scenery : list) {
			Stunion m2 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+scenery.getStunionId()+"' ");
			if(m2 != null){
				scenery.setStunionIdTxt(m2.getName());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,Scenery model){
		//判断是否已存在
		Scenery model2 = (Scenery) baseService.findObject(" from "+new Scenery().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		Scenery model = (Scenery) baseService.getById(new Scenery(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,Scenery model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,Scenery model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
