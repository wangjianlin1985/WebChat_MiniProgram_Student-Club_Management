package com.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.StringUtil;
import com.base.util.field.FieldUtil;
import com.base.util.page.PageUtils;
import com.model.Study;

@Controller
@RequestMapping(value = "admin/study")
public class StudyController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "admin/study/list";//跳转到WebContent/WEB-INF/views/study/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		return "admin/study/table";//跳转到WebContent/WEB-INF/views/study/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		return "admin/study/add";//跳转到WebContent/WEB-INF/views/study/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		Study model = (Study) baseService.getById(new Study(), id);
		request.setAttribute("model", model);
		return "admin/study/edit";//跳转到WebContent/WEB-INF/views/study/edit.html页面
	}
	
	// 获取列表数据
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new Study().getClass().getName()+" where 1=1 ";
		//标题查询
		String title = request.getParameter("title");
		if(!StringUtil.isEmpty(title)){
			hql += " and title like '%"+title+"%' ";
		}
		page = baseService.findPageList(hql, page);
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,Study model){
		//判断是否已存在
		Study model2 = (Study) baseService.findObject(" from "+new Study().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		Study model = (Study) baseService.getById(new Study(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,Study model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,Study model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
