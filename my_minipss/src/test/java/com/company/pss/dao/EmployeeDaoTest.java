package com.company.pss.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.company.pss.domain.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
public class EmployeeDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private IEmployeeDao dao;

	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setName("name");
		e.setAge(10);
		e.setEmail("email");
		e.setPassword("password");
		for (int i = 0; i < 20; i++) {
			dao.save(e);
		}
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testGet() {
	}

	@Test
	public void testList() {
	}

	@Test
	public void testQuery() {
		List<Employee> list = dao.list();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

}
