package com.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.StringUtil;
import com.base.util.field.FieldUtil;
import com.base.util.page.PageUtils;
import com.model.Activity;
import com.model.ActivityApply;
import com.model.Dict;
import com.model.Student;

@Controller
@RequestMapping(value = "admin/activityApply")
public class ActivityApplyController extends BaseController {
	
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
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `activity_apply_status` ");
		request.setAttribute("statuss", statuss);
		List<?> iscredits = (List<?>) baseService.findListByHql(" from Dict where code = `is_credit` ");
		request.setAttribute("iscredits", iscredits);
		return "admin/activityApply/list";//跳转到WebContent/WEB-INF/views/activityApply/list.html页面
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
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `activity_apply_status` ");
		request.setAttribute("statuss", statuss);
		List<?> iscredits = (List<?>) baseService.findListByHql(" from Dict where code = `is_credit` ");
		request.setAttribute("iscredits", iscredits);
		return "admin/activityApply/table";//跳转到WebContent/WEB-INF/views/activityApply/table.html页面
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
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `activity_apply_status` ");
		request.setAttribute("statuss", statuss);
		List<?> iscredits = (List<?>) baseService.findListByHql(" from Dict where code = `is_credit` ");
		request.setAttribute("iscredits", iscredits);
		return "admin/activityApply/add";//跳转到WebContent/WEB-INF/views/activityApply/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		ActivityApply model = (ActivityApply) baseService.getById(new ActivityApply(), id);
		request.setAttribute("model", model);
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		String hql = " from Activity where 1=1 ";
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"') ";
		}
		List<?> activityIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("activityIds", activityIds);
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `activity_apply_status` ");
		request.setAttribute("statuss", statuss);
		List<?> iscredits = (List<?>) baseService.findListByHql(" from Dict where code = `is_credit` ");
		request.setAttribute("iscredits", iscredits);
		return "admin/activityApply/edit";//跳转到WebContent/WEB-INF/views/activityApply/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new ActivityApply().getClass().getName()+" where 1=1 ";
		//学生查询
		String studentId = request.getParameter("studentId");
		if(!StringUtil.isEmpty(studentId)){
			hql += " and studentId in (select id from Student where 1=1 and name like '%"+studentId+"%') ";
		}
		//申请状态查询
		String status = request.getParameter("status");
		if(!StringUtil.isEmpty(status)){
			hql += " and status in (select val from Dict where code = `activity_apply_status` and txt like '%"+status+"%') ";
		}
		//是否打分查询
		String iscredit = request.getParameter("iscredit");
		if(!StringUtil.isEmpty(iscredit)){
			hql += " and iscredit in (select val from Dict where code = `is_credit` and txt like '%"+iscredit+"%') ";
		}
		//学分查询
		String credit = request.getParameter("credit");
		if(!StringUtil.isEmpty(credit)){
			hql += " and credit = '"+credit+"' ";
		}
		if(getSessionUser(request).getRole().equals("leader")) {
			hql += " and activityId in ( select id from Activity where  stunionId in (select id from Stunion where userId = '"+getSessionUser(request).getId()+"')) ";
		}
		List<?> activityIds = (List<?>) baseService.findListByHql(hql);
		request.setAttribute("activityIds", activityIds);
		page = baseService.findPageList(hql, page);
		List<ActivityApply> list = (List<ActivityApply>) page.getData();
		for (ActivityApply activityApply : list) {
			Student m2 = (Student) baseService.findObject(" from Student where 1=1 and id = '"+activityApply.getStudentId()+"' ");
			if(m2 != null){
				activityApply.setStudentIdTxt(m2.getName());
			}
			Activity m3 = (Activity) baseService.findObject(" from Activity where 1=1 and id = '"+activityApply.getActivityId()+"' ");
			if(m3 != null){
				activityApply.setActivityIdTxt(m3.getName());
			}
			Dict m5 = (Dict) baseService.findObject(" from Dict where code = `activity_apply_status` and val = '"+activityApply.getStatus()+"' ");
			if(m5 != null){
				activityApply.setStatusTxt(m5.getTxt());
			}
			Dict m6 = (Dict) baseService.findObject(" from Dict where code = `is_credit` and val = '"+activityApply.getIscredit()+"' ");
			if(m6 != null){
				activityApply.setIscreditTxt(m6.getTxt());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,ActivityApply model){
		//判断是否已存在
		ActivityApply model2 = (ActivityApply) baseService.findObject(" from "+new ActivityApply().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		ActivityApply model = (ActivityApply) baseService.getById(new ActivityApply(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,ActivityApply model){
		ActivityApply bean = (ActivityApply) baseService.getById(new ActivityApply(), model.getId());
		boolean flag = baseService.merge(request,model);
		if(flag){
			if(getSessionUser(request).getRole().equals("leader")) {
				String status = request.getParameter("status");
				if(!StringUtil.isEmpty(status)) {
					int credit = model.getCredit()-bean.getCredit();
					if(status.equals("1")){
						baseService.execute(" update Student set credit=credit+"+credit+" where id = '"+model.getStudentId()+"' ");
					}
				}
			}
			result = successResult("");
		}else{
			result = failResult("修改失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
	// 删除方法
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,ActivityApply model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
