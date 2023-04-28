package com.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.base.util.DateUtil;
import com.base.util.field.FieldUtil;
import com.base.util.page.Page;

/***
 * 数据访问层
 */
@SuppressWarnings("all")
@Repository
//@Service用于标注业务层组件
//@Controller用于标注控制层组件（如struts中的action）
//@Repository用于标注数据访问组件，即DAO组件
//@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
public class BaseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(Object o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(Object o) {
		this.getCurrentSession().delete(o);
	}
	
	public void deletes(Object o,String ids) {
		String hql = " delete from "+o.getClass().getSimpleName() +" where id in ("+ids+")";
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public void update(Object o) {
		this.getCurrentSession().merge(o);
	}
	
	public void merge(Object obj,List<String> fields,List<String> values) {
		int id = (int) FieldUtil.getFieldValueByName("id", obj);
		String hql = " update "+obj.getClass().getSimpleName() +" set id='"+id+"' ";
		for (int i=0; i<fields.size(); i++) {
			hql += ","+fields.get(i)+"='"+values.get(i)+"' ";
		}
		if(FieldUtil.isExistField("updatedAt", obj)){
			hql += ",updatedAt = '"+DateUtil.getNow()+"'";
		}
		hql += " where id='"+id+"' ";
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public void saveOrUpdate(Object o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<?> findHQL(String hql) {
		hql = hql.replaceAll("`", "'");
		return this.getCurrentSession().createQuery(hql).list();
	}
	
	public List<Object> findSQL(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).list();
	}

	public List<Object> find(String hql, Object[] param) {
		hql = hql.replaceAll("`", "'");
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<Object> find(String hql, List<Object> param) {
		hql = hql.replaceAll("`", "'");
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}
	
	public List<Object> find(String hql, Object[] param, Integer page, Integer rows) {
		hql = hql.replaceAll("`", "'");
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<Object> find(String hql, List<Object> param, Integer page, Integer rows) {
		hql = hql.replaceAll("`", "'");
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	public Object get(Class c, Serializable id) {
		return  this.getCurrentSession().get(c, id);
	}

	public Object get(String hql) {
		hql = hql.replaceAll("`", "'");
		List<?> l = this.findHQL(hql);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
	
	public Object get(String hql, Object[] param) {
		hql = hql.replaceAll("`", "'");
		List<Object> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Object get(String hql, List<Object> param) {
		hql = hql.replaceAll("`", "'");
		List<Object> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public int count(String hql) {
		hql = hql.replaceAll("`", "'");
		return this.getCurrentSession().createQuery(hql).list().size();
	}

	public Long count(String hql, Object[] param) {
		hql = hql.replaceAll("`", "'");
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		int size = q.list().size();
		return new Long((long)size);
	}

	public Long count(String hql, List<Object> param) {
		hql = hql.replaceAll("`", "'");
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		int size = q.list().size();
		return new Long((long)size);
	}

	public Integer executeHql(String hql) {
		hql = hql.replaceAll("`", "'");
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		hql = hql.replaceAll("`", "'");
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		hql = hql.replaceAll("`", "'");
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

}
