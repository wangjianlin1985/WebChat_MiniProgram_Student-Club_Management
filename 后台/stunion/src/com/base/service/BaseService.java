package com.base.service;


import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.base.util.page.Page;

/**
 * 业务层
 */
public interface BaseService {
	
	/**
	 * 通过ID查询实体
	 * @param obj
	 * @param id
	 * @return
	 */
	public Object getById(Object obj,Serializable id);

	/**
	 * 添加
	 * @param obj
	 */
	public boolean save(Object obj);
	
	/**
	 * 修改
	 * @param obj
	 */
	public boolean update(Object obj);
	/**
	 * 动态修改
	 * @param obj
	 */
	public boolean merge(HttpServletRequest request,Object obj);
	/**
	 * 执行hql语句(删除/修改)
	 * @param obj
	 */
	public boolean execute(String hql);
	
	/**
	 * 删除
	 * @param obj
	 */
	public boolean delete(Object obj);
	/**
	 * 删除多条数据
	 * @param obj
	 */
	public boolean deletes(Object obj,String ids);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<?> findAllList(Object obj);
	/**
	 * 查询所有数据HQL
	 * @return
	 */
	public List<?> findListByHql(String hql);
	/**
	 * 查询所有数据SQL
	 * @return
	 */
	public List<?> findListBySql(String sql);
	
	/**
	 * 根据条件查询数据 分页
	 * @param obj
	 * @return
	 */
	public List<?> findList(String hql, Object[] param, Integer page, Integer rows);
	/**
	 * 根据条件查询数据 无分页
	 * @param obj
	 * @return
	 */
	public List<?> findList(String hql, Object[] param);
	
	/**
	 * 通过条件查找实体
	 * @param obj
	 * @return
	 */
	public Object findObject(String hql);
	
	/**
	 * 查询实体分页列表
	 * @param obj
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page findPageList(Object obj,Page page)throws Exception;
	
	/**
	 * 查询实体分页列表
	 * @param obj
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page findPageList(String hql,Page page)throws Exception;
	
	
}
