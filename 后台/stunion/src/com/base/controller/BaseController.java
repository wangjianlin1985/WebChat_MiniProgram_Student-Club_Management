package com.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.service.BaseService;
import com.base.util.JsonResult;
import com.base.util.StringUtil;
import com.base.util.page.Page;
import com.model.User;

import net.sf.json.JSONObject;

@Controller
public class BaseController {

	@Autowired
	public BaseService baseService;
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	@Autowired
	public JsonResult result;
	public Page page;

	public ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping(value = "response")
	public void response(HttpServletRequest request,HttpServletResponse response){
		/*Enumeration<?> enu = request.getAttributeNames();
		while (enu.hasMoreElements()) {
			Object object = (Object) enu.nextElement();
			System.out.println(object.toString());
		}*/
		
		Enumeration<?> enu = request.getParameterNames();  
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			System.out.println(paraName+": "+request.getParameter(paraName));  
		}
		outJson(response, "ok");
	}
	
	//hql += getQuery(request, new ${Model.lm}().getSearchFields());
	public String getQuery(HttpServletRequest request,String searchFields) {
		String hql = "";
		String[] fields = searchFields.split(";");
		for (String field : fields) {
			String[] kw = field.split(":");
			if(kw.length==2 && !StringUtil.isEmpty(kw[0]) && !StringUtil.isEmpty(request.getParameter(kw[0]))) {
				if(kw[1].equalsIgnoreCase("like")){
					hql += " and "+kw[0]+" like '%"+request.getParameter(kw[0])+"%'";
				}else {
					hql += " and "+kw[0]+" "+kw[1]+" '"+request.getParameter(kw[0])+"'";
				}
			}
		}
		return hql;
	}
	
	public User getSessionUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("User");
		return user;
	}

	/***
	 * 相应请求 
	 * 给前台返回json字符串{"sign":"1",reason:"success","result":[{"id":"1","name":
	 * "zhangsan"},{"id":"1","name":"zhangsan"}]}
	 * @param response
	 * @param result 自定义json实体sign reason result共三个属性
	 */
	public void outJson(HttpServletResponse response, JsonResult result) {
		try {
			StringWriter writer = new StringWriter();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(writer, result);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void outGridJson(HttpServletResponse response, Page page) {
		response.setContentType("application/json");
		JSONObject jo = new JSONObject();
		jo.put("total", page.getCount());
		jo.put("rows", page.getData());
		try {
			objectMapper.writeValue(response.getOutputStream(), jo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outTableJson(HttpServletResponse response, Page page) {
		response.setContentType("application/json");
		JSONObject jo = new JSONObject();
		jo.put("count", page.getCount());
		jo.put("data", page.getData());
		try {
			jo.put("code", 0);
			jo.put("msg", "ok");
			objectMapper.writeValue(response.getOutputStream(), jo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void outJson(HttpServletResponse response, Object obj) {
		response.setContentType("application/json");
		try {
			objectMapper.writeValue(response.getOutputStream(), obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public JsonResult failResult(String reason) {
		result.setCode("0");
		result.setData(null);
		result.setMsg(reason);
		return result;
	}

	public JsonResult successResult(Object obj) {
		result.setCode("1");
		result.setData(obj);
		result.setMsg("success");
		return result;
	}

}
