package com.xmg.minipss.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.xmg.minipss.dao.IGenericDAO;
import com.xmg.minipss.domain.BaseDomain;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

@SuppressWarnings("all")
public class GenericDAOImpl<T extends BaseDomain> implements IGenericDAO<T> {

	protected SessionFactory sessionFactory;
	private Class<?> targetClass;

	public GenericDAOImpl() {
		//得到真实的T是谁
		targetClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(T o) {
		Session session = sessionFactory.getCurrentSession();
		session.save(o);
	}

	@Override
	public void update(T o) {
		Session session = sessionFactory.getCurrentSession();
		session.update(o);
	}

	@Override
	public void delete(T o) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(o);
	}

	@Override
	public T get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(targetClass, id);
	}

	@Override
	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(targetClass).list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder totalCountHQL = new StringBuilder(100)
				.append("SELECT COUNT(obj) FROM ")
				.append(targetClass.getSimpleName()).append(" obj");
		totalCountHQL.append(qo.getQuery());
		Query query = session.createQuery(totalCountHQL.toString());
		setParameters(qo, query);
		Integer totalCount = ((Long) query.uniqueResult()).intValue();
		if (totalCount > 0) {
			StringBuilder hql = new StringBuilder(100)
					.append("SELECT obj FROM ")
					.append(targetClass.getSimpleName()).append(" obj")
					.append(qo.getQuery());
			query = session.createQuery(hql.toString());
			setParameters(qo, query);
			query.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize())
					.setMaxResults(qo.getPageSize());
			List<T> ret = query.list();
			return new PageResult(totalCount, qo.getPageSize(),
					qo.getCurrentPage(), ret);
		}
		return PageResult.empty(qo.getPageSize());
	}

	private void setParameters(QueryObject qo, Query query) {
		for (int i = 0; i < qo.getParams().size(); i++) {
			query.setParameter(i, qo.getParams().get(i));
		}
	}

}
