package com.company.pss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.company.pss.dao.ISystemMenuDao;
import com.company.pss.domain.Role;
import com.company.pss.domain.SystemMenu;

public class SystemMenuDaoImpl extends GenericDaoImpl<SystemMenu> implements
		ISystemMenuDao {

	public List<SystemMenu> queryMenuChildren(String sn) {
		Session session = super.sessionFactory.getCurrentSession();
		String hql = "select obj from SystemMenu obj where obj.parent.sn = :sn";
		Query query = session.createQuery(hql).setParameter("sn", sn);
		@SuppressWarnings("unchecked")
		List<SystemMenu> list = query.list();
		return list;
	}

	public List<SystemMenu> queryMenuChildren(List<Role> roles, String sn) {
		Session session = super.sessionFactory.getCurrentSession();
		String hql = "select m from Role r left join r.systemMenus m where r in (:roles) and m.parent.sn = :sn";
		Query query = session.createQuery(hql).setParameter("sn", sn).setParameterList("roles", roles);
		@SuppressWarnings("unchecked")
		List<SystemMenu> list = query.list();
		return list;
	}

}
