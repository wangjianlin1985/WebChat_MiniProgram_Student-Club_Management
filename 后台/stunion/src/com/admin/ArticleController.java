package com.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.field.FieldUtil;
import com.base.util.page.PageUtils;
import com.model.Article;

@Controller
@RequestMapping(value = "admin/article")
public class ArticleController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "admin/article/list";//跳转到WebContent/WEB-INF/views/article/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		return "admin/article/table";//跳转到WebContent/WEB-INF/views/article/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		return "admin/article/add";//跳转到WebContent/WEB-INF/views/article/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		Article model = (Article) baseService.getById(new Article(), id);
		request.setAttribute("model", model);
		return "admin/article/edit";//跳转到WebContent/WEB-INF/views/article/edit.html页面
	}
	
	// 获取列表数据
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		page = PageUtils.getPage(request);//获取请求分页参数信息
		String hql = " from "+new Article().getClass().getName()+" where 1=1 ";
		hql += getQuery(request, new Article().getSearchFields());
		page = baseService.findPageList(hql, page);
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,Article model){
		//判断是否已存在
		Article model2 = (Article) baseService.findObject(" from "+new Article().getClass().getName()+" where id = '"+model.getId()+"' ");
		if(model2!=null) {
			result = failResult("操作失败，主键已存在!");
		}else {
			FieldUtil.setCreatedAt(model);
			boolean flag = baseService.save(model);
			if(flag){
				result = successResult(model);
			}else{
				result = failResult("新增失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	
	// 根据id查询
	@RequestMapping(value = "show")
	public void show(HttpServletRequest request,HttpServletResponse response,int id){
		Article model = (Article) baseService.getById(new Article(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,Article model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,Article model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
