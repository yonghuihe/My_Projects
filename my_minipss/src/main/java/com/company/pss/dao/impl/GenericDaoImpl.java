package com.company.pss.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.company.pss.dao.IGenericDao;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

@SuppressWarnings("all")
public class GenericDaoImpl<T> implements IGenericDao<T> {
	protected SessionFactory sessionFactory;
	private Class<?> targetClass;

	/**
	 * 得到真实的T是谁
	 */
	public GenericDaoImpl() {
		targetClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(T o) {
		/**
		 * 注意： 1、这里不能sessionFactory.openSession()，session的开启在service层
		 * 2、这里不能手动关闭session（session.close();）
		 * 
		 */
		Session session = sessionFactory.getCurrentSession();
		session.save(o);
	}

	public void update(T o) {
		Session session = sessionFactory.getCurrentSession();
		session.update(o);
	}

	public void delete(T o) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(o);
	}

	public T get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		T T = (T) session.get(targetClass, id);
		return T;
	}

	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 根据类型查询： 相当于：SELECT e REOM Employee e
		 */
		List<T> list = session.createCriteria(targetClass).list();
		return list;
	}

	/**
	 * 如果totalCount为0，就返回一个空的PageResult
	 */
	public PageResult query(QueryObject qo) {
		Session session = sessionFactory.getCurrentSession();
		// 拼接HQL
		StringBuilder taotalCountHQL = new StringBuilder(100).append("SELECT count(obj) FROM ")
				.append(targetClass.getSimpleName()).append(" obj ").append(qo.getQuery());
		Query query = session.createQuery(taotalCountHQL.toString());
		// 设置参数
		setParameters(qo, query);
		Integer totalCount = ((Long) query.uniqueResult()).intValue();// 总记录数
		if (totalCount > 0) {
			// 拼接HQL
			StringBuilder hql = new StringBuilder(100).append("SELECT obj FROM ")
					.append(targetClass.getSimpleName())
					.append(" obj ").append(qo.getQuery());
			query = session.createQuery(hql.toString());
			// 设置参数
			setParameters(qo, query);
			// 设置当前页和页面容量（相当于 limit currentPage,pageSize）
			query.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize());
			query.setMaxResults(qo.getPageSize());
			List<T> result = query.list();
			return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, result);
		}
		return new PageResult().empty(qo.getPageSize());
	}

	private void setParameters(QueryObject qo, Query query) {
		for (int i = 0; i < qo.getParams().size(); i++) {
			query.setParameter(i, qo.getParams().get(i));
		}
	}

}
