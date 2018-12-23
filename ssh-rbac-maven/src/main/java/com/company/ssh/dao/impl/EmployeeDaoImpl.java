package com.company.ssh.dao.impl;

import com.company.ssh.dao.IEmployeeDao;
import com.company.ssh.domain.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements IEmployeeDao {

	public Employee login(String name, String password) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(name+","+password);
		Query query = session.createQuery("from Employee e where e.name = ? and e.password = ?")
				.setString(0,name).setString(1,password);
		List<Employee> list = query.list();
		if (list.size() == 1){
			return list.get(0);
		}
		return null;
	}
}
