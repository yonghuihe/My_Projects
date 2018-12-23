package com.xmg.minipss.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.xmg.minipss.dao.ISystemMenuDAO;
import com.xmg.minipss.domain.Role;
import com.xmg.minipss.domain.SystemMenu;

@SuppressWarnings("all")
public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements
		ISystemMenuDAO {

	@Override
	public List<SystemMenu> listChildren() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(
				"SELECT m FROM SystemMenu m WHERE m.parent IS NOT NULL").list();
	}

	@Override
	public List<SystemMenu> loadMenusByParentSn(String parentSn) {
		Session session = this.sessionFactory.getCurrentSession();
		return session
				.createQuery("SELECT m FROM SystemMenu m WHERE m.parent.sn = ?")
				.setParameter(0, parentSn).list();
	}

	@Override
	public List<SystemMenu> loadMenusByParentSnAndRoles(String parentSn,
			List<Role> roles) {
		Session session = this.sessionFactory.getCurrentSession();
		return session
				.createQuery(
						"SELECT m FROM Role r JOIN r.menus m WHERE m.parent.sn = :parentSn AND r in(:roles)")
				.setParameter("parentSn", parentSn)
				.setParameterList("roles", roles).list();
	}

}
