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
@RequestMapping(value = "admin/${Model.lm?uncap_first}")
public class ${Model.lm}Controller extends BaseController {
	
	// 列表页面
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		<#list items as item>
		<#if (item.kjzd?? && item.kjzd!='')>
		List<?> ${item.lmc}s = (List<?>) baseService.findListByHql(" ${item.kjzd} ");
		request.setAttribute("${item.lmc}s", ${item.lmc}s);
		</#if>
		</#list>
		return "admin/${Model.lm?uncap_first}/list";//跳转到WebContent/WEB-INF/views/${Model.lm?uncap_first}/list.html页面
	}

	// 表格页面
	@RequestMapping(value = "table")
	public String table(HttpServletRequest request,HttpServletResponse response){
		<#list items as item>
		<#if (item.kjzd?? && item.kjzd!='')>
		List<?> ${item.lmc}s = (List<?>) baseService.findListByHql(" ${item.kjzd} ");
		request.setAttribute("${item.lmc}s", ${item.lmc}s);
		</#if>
		</#list>
		return "admin/${Model.lm?uncap_first}/table";//跳转到WebContent/WEB-INF/views/${Model.lm?uncap_first}/table.html页面
	}
	
	// 新增页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,HttpServletResponse response){
		<#list items as item>
		<#if (item.kjzd?? && item.kjzd!='')>
		List<?> ${item.lmc}s = (List<?>) baseService.findListByHql(" ${item.kjzd} ");
		request.setAttribute("${item.lmc}s", ${item.lmc}s);
		</#if>
		</#list>
		return "admin/${Model.lm?uncap_first}/add";//跳转到WebContent/WEB-INF/views/${Model.lm?uncap_first}/add.html页面
	}

	// 编辑页面
	@RequestMapping(value = "edit/{id}")
	public String edit(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		${Model.lm} model = (${Model.lm}) baseService.getById(new ${Model.lm}(), id);
		request.setAttribute("model", model);
		<#list items as item>
		<#if (item.kjzd?? && item.kjzd!='')>
		List<?> ${item.lmc}s = (List<?>) baseService.findListByHql(" ${item.kjzd} ");
		request.setAttribute("${item.lmc}s", ${item.lmc}s);
		</#if>
		</#list>
		return "admin/${Model.lm?uncap_first}/edit";//跳转到WebContent/WEB-INF/views/${Model.lm?uncap_first}/edit.html页面
	}
	
	// 获取列表数据
	@RequestMapping(value = "getTable")
	public void getTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取表格分页数据信息，进行数据分页
		page = PageUtils.getPage(request);
		String hql = " from "+new ${Model.lm}().getClass().getName()+" where 1=1 ";
		<#list schs as item>
		//${item.lbz}查询
		String ${item.lmc} = request.getParameter("${item.lmc}");
		if(!StringUtil.isEmpty(${item.lmc})){
			<#if (item.kjzd?? && item.kjzd!='')>
			hql += " and ${item.lmc} in (select ${item.kjz} ${item.kjzd} and ${item.kjxs} like '%"+${item.lmc}+"%') ";
			<#else>
				<#if (item.cxlx?? && item.cxlx!='like')>
			hql += " and ${item.lmc} ${item.cxlx} '"+${item.lmc}+"' ";
				<#else>
			hql += " and ${item.lmc} like '%"+${item.lmc}+"%' ";
				</#if>
			</#if>
		}
		</#list>
		page = baseService.findPageList(hql, page);
		List<${Model.lm}> list = (List<${Model.lm}>) page.getData();
		for (${Model.lm} ${Model.lm?uncap_first} : list) {
			<#list items as item>
			<#if item.kjzd?? && item.kjzd!=''>
			${item.kjl} m${item_index+1} = (${item.kjl}) baseService.findObject(" ${item.kjzd} and ${item.kjz} = '"+${Model.lm?uncap_first}.get${item.lmc?cap_first}()+"' ");
			if(m${item_index+1} != null){
				${Model.lm?uncap_first}.set${item.lmc?cap_first}Txt(m${item_index+1}.get${item.kjxs?cap_first}());
			}
			</#if>
			</#list>
		}
		outTableJson(response, page);
	}
	
	// 新增保存
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,${Model.lm} model){
		//判断是否已存在
		${Model.lm} model2 = (${Model.lm}) baseService.findObject(" from "+new ${Model.lm}().getClass().getName()+" where id = '"+model.getId()+"' ");
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
		${Model.lm} model = (${Model.lm}) baseService.getById(new ${Model.lm}(), id);
		result = successResult(model);
		outJson(response, result);
	}
	
	// 修改方法
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,HttpServletResponse response,${Model.lm} model){
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
	public void delete(HttpServletRequest request,HttpServletResponse response,${Model.lm} model){
		boolean flag = baseService.delete(model);
		if(flag){
			result = successResult(model);
		}else{
			result = failResult("删除失败，系统发生异常...");
		}
		outJson(response, result);
	}
	
}
