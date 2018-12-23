package com.company.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.company.ssh.dao.IEmployeeDao;
import com.company.ssh.domain.Employee;

@SuppressWarnings("all")
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements IEmployeeDao {

	@Override
	public Employee login(String name, String password) {
		Session session = sessionFactory.getCurrentSession();
		List<Employee> list = session.createQuery("SELECT e FROM Employee e WHERE e.name = ? AND e.password = ?")
				.setParameter(0, name).setParameter(1, password).list();
		if (list.size() == 1){
			return list.get(0);
		}
		return null;
	}
}
