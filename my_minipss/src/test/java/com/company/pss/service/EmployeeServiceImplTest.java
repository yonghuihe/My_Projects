package com.company.pss.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.pss.domain.Employee;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceImplTest {
	@Autowired
	private IEmployeeService service;

	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setName("name");
		e.setAge(11);
		e.setEmail("email");
		e.setPassword("password");
		service.save(e);
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
		List<Employee> list = service.list();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

}
