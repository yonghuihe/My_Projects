package com.xmg.minipss.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.xmg.minipss.dao.IEmployeeDAO;
import com.xmg.minipss.domain.Employee;

@SuppressWarnings("all")
public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements
		IEmployeeDAO {

	@Override
	public Employee login(String name, String password) {
		Session session = sessionFactory.getCurrentSession();
		List<Employee> emps = session
				.createQuery(
						"SELECT e FROM Employee e WHERE e.name = ? AND e.password = ?")
				.setParameter(0, name).setParameter(1, password).list();
		if (emps.size() == 1) {
			return emps.get(0);
		}
		return null;
	}

	@Override
	public void deleteBatch(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("DELETE FROM Employee e WHERE e.id IN (:ids)")
				.setParameterList("ids", ids).executeUpdate();
	}

}
