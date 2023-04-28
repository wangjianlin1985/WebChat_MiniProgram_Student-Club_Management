package com.base.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.base.dao.BaseDAO;
import com.base.service.BaseService;
import com.base.util.field.FieldUtil;
import com.base.util.page.Page;

/**
 * 业务层实现类
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	@Resource
	private BaseDAO dao;
	public BaseDAO getDao() {
		return dao;
	}
	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	
	@Override
	public boolean save(Object obj) {
		try {
			dao.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean update(Object obj) {
		try {
			dao.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean merge(HttpServletRequest request,Object obj) {
		try {
			Enumeration<?> enu = request.getParameterNames();  
			List<String> fields = new ArrayList<String>(); 
			List<String> values = new ArrayList<String>(); 
			while(enu.hasMoreElements()){  
				String paraName=(String)enu.nextElement();  
				//System.out.println(paraName+": "+request.getParameter(paraName));  
				if(FieldUtil.isExistField(paraName, obj)) {
					fields.add(paraName);
					values.add(request.getParameter(paraName));
				}
			}
			dao.merge(obj,fields,values);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean execute(String hql) {
		try {
			dao.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	@Override
	public boolean delete(Object obj) {
		try {
			dao.delete(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deletes(Object obj,String ids){
		try {
			dao.deletes(obj,ids);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	@Override
	public List<?> findAllList(Object obj) {
		List<?> list = dao.findHQL("from "+obj.getClass().getName());
		return list;
	}

	
	@Override
	public List<?> findList(String hql, Object[] param, Integer page, Integer rows) {
		return dao.find(hql, param, page, rows);
	}
	
	@Override
	public List<?> findList(String hql, Object[] param) {
		return dao.find(hql, param);
	}

	
	@Override
	public Object findObject(String hql) {
		return dao.get(hql);
	}

	
	@Override
	public Object getById(Object obj,Serializable id) {
		return dao.get(obj.getClass(), id);
	}

	
	@Override
	public Page findPageList(Object obj,Page page) throws Exception {
		StringBuffer hql = new StringBuffer(" from "+obj.getClass().getSimpleName());
		List<?> list = dao.find(hql.toString(), new Object[]{}, Integer.valueOf(page.getPage()), Integer.valueOf(page.getLimit()));
		int count = dao.count(hql.toString());
		page.setData(list);
		page.setCount(count);
		return page;
	}
	
	@Override
	public Page findPageList(String hql,Page page) throws Exception {
		List<?> list = dao.find(hql.toString(), new Object[]{}, Integer.valueOf(page.getPage()), Integer.valueOf(page.getLimit()));
		int count = dao.count(hql.toString());
		page.setData(list);
		page.setCount(count);
		return page;
	}
	
	@Override
	public List<?> findListByHql(String hql) {
		return dao.findHQL(hql);
	}
	@Override
	public List<?> findListBySql(String sql) {
		return dao.findSQL(sql);
	}

	

}
