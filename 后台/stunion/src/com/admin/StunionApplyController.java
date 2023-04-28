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
@RequestMapping(value = "admin/stunionApply")
public class StunionApplyController extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		List<?> stunionIds = (List<?>) baseService.findListByHql(" from Stunion where 1=1 ");
		request.setAttribute("stunionIds", stunionIds);
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `stunion_apply_status` ");
		request.setAttribute("statuss", statuss);
		return "admin/stunionApply/list";//跳转到WebContent/WEB-INF/views/stunionApply/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		List<?> stunionIds = (List<?>) baseService.findListByHql(" from Stunion where 1=1 ");
		request.setAttribute("stunionIds", stunionIds);
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `stunion_apply_status` ");
		request.setAttribute("statuss", statuss);
		return "admin/stunionApply/table";//跳转到WebContent/WEB-INF/views/stunionApply/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		List<?> stunionIds = (List<?>) baseService.findListByHql(" from Stunion where 1=1 ");
		request.setAttribute("stunionIds", stunionIds);
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `stunion_apply_status` ");
		request.setAttribute("statuss", statuss);
		return "admin/stunionApply/add";//跳转到WebContent/WEB-INF/views/stunionApply/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		StunionApply model = (StunionApply) baseService.getById(new StunionApply(), id);
		request.setAttribute("model", model);
		List<?> studentIds = (List<?>) baseService.findListByHql(" from Student where 1=1 ");
		request.setAttribute("studentIds", studentIds);
		List<?> stunionIds = (List<?>) baseService.findListByHql(" from Stunion where 1=1 ");
		request.setAttribute("stunionIds", stunionIds);
		List<?> statuss = (List<?>) baseService.findListByHql(" from Dict where code = `stunion_apply_status` ");
		request.setAttribute("statuss", statuss);
		return "admin/stunionApply/edit";//跳转到WebContent/WEB-INF/views/stunionApply/edit.html页面
	}
	
	// 获取列表数据
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new StunionApply().getClass().getName()+" where 1=1 ";
		//学生查询
		String studentId = request.getParameter("studentId");
		if(!StringUtil.isEmpty(studentId)){
			hql += " and studentId in (select id from Student where 1=1 and name like '%"+studentId+"%') ";
		}
		//申请状态查询
		String status = request.getParameter("status");
		if(!StringUtil.isEmpty(status)){
			hql += " and status in (select val from Dict where code = `stunion_apply_status` and txt like '%"+status+"%') ";
		}
		page = baseService.findPageList(hql, page);
		List<StunionApply> list = (List<StunionApply>) page.getData();
		for (StunionApply stunionApply : list) {
			Student m2 = (Student) baseService.findObject(" from Student where 1=1 and id = '"+stunionApply.getStudentId()+"' ");
			if(m2 != null){
				stunionApply.setStudentIdTxt(m2.getName());
			}
			Stunion m3 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+stunionApply.getStunionId()+"' ");
			if(m3 != null){
				stunionApply.setStunionIdTxt(m3.getName());
			}
			Dict m5 = (Dict) baseService.findObject(" from Dict where code = `stunion_apply_status` and val = '"+stunionApply.getStatus()+"' ");
			if(m5 != null){
				stunionApply.setStatusTxt(m5.getTxt());
			}
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,StunionApply model){
		//判断是否已存在
		StunionApply model2 = (StunionApply) baseService.findObject(" from "+new StunionApply().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		StunionApply model = (StunionApply) baseService.getById(new StunionApply(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,StunionApply model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,StunionApply model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
