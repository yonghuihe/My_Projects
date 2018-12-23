package com._520it.ssh.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com._520it.ssh.dao.IEmployeeDAO;
import com._520it.ssh.domain.Employee;

import lombok.Setter;

public class EmployeeDAOImpl implements IEmployeeDAO {
	@Setter
	private SessionFactory sessionFactory; 
	@Override
	public void save(Employee e) {
		Session session = sessionFactory.getCurrentSession();
		session.save(e);
	}

	@Override
	public void delete(Employee e) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(e);
	}

	@Override
	public void update(Employee e) {
		Session session = sessionFactory.getCurrentSession();
		session.update(e);
	}

	@Override
	public Employee get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Employee) session.get(Employee.class, id);
	}

	@Override
	public List<Employee> list() {
		String hql = "select obj from Employee obj"; 
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}
}
