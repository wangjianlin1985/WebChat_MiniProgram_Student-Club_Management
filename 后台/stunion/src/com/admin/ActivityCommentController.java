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
@RequestMapping(value = "admin/activityComment")
public class ActivityCommentController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		String hql = " from Activity where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"') ";
		}
		List<?> activityIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("activityIds", activityIds);
		return "admin/activityComment/list";//跳转到WebContent/WEB-INF/views/activityComment/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		String hql = " from Activity where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"') ";
		}
		List<?> activityIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("activityIds", activityIds);
		return "admin/activityComment/table";//跳转到WebContent/WEB-INF/views/activityComment/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		String hql = " from Activity where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"') ";
		}
		List<?> activityIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("activityIds", activityIds);
		return "admin/activityComment/add";//跳转到WebContent/WEB-INF/views/activityComment/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		ActivityComment model = (ActivityComment) baseService.getById(new ActivityComment(), id);
		request.setAttribute("model", model);
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		String hql = " from Activity where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"') ";
		}
		List<?> activityIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("activityIds", activityIds);
		return "admin/activityComment/edit";//跳转到WebContent/WEB-INF/views/activityComment/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new ActivityComment().getClass().getName()+" where 1=1 ";
		//学生查询
		String studentId = request.getParameter("studentId");
		if(!StringUtil.isEmpty(studentId)){
			hql += " and studentId in (select id from Student where 1=1 and name like '%"+studentId+"%') ";
		}
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and activityId in ( select id from Activity where  stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"')) ";
		}
		page = baseService.findPageList(hql, page);
		List<ActivityComment> list = (List<ActivityComment>) page.getData();
		for (ActivityComment activityComment : list) {
			Student m2 = (Student) baseService.findObject(" from Student where 1=1 and id = '"+activityComment.getStudentId()+"' ");
			if(m2 != null){
				activityComment.setStudentIdTxt(m2.getName());
			}
			Activity m3 = (Activity) baseService.findObject(" from Activity where 1=1 and id = '"+activityComment.getActivityId()+"' ");
			if(m3 != null){
				activityComment.setActivityIdTxt(m3.getName());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,ActivityComment model){
		//判断是否已存在
		ActivityComment model2 = (ActivityComment) baseService.findObject(" from "+new ActivityComment().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		ActivityComment model = (ActivityComment) baseService.getById(new ActivityComment(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,ActivityComment model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,ActivityComment model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
