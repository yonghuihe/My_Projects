package com._520it.ssh.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.ssh.domain.Employee;
import com._520it.ssh.service.IEmployeeService;
import com._520it.ssh.service.impl.EmployeeServiceImpl;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
	@Autowired
	private IEmployeeService service;
	@Test
	public void testSave() throws Exception {
		Employee e = new Employee(null, "张三", new BigDecimal("666"), new Date());
		service.save(e);
	}
	@Test
	public void testDelete() throws Exception {
		Employee e = new Employee();
		e.setId(1L);
		service.delete(e);
	}
	@Test
	public void testUpdate() throws Exception {
		Employee e = new Employee(2L, "李四", new BigDecimal("4444"), new Date());
		service.update(e);
	}
	@Test
	public void testGet() throws Exception {
		Employee employee = service.get(2L);
		System.out.println(employee);
	}
	@Test
	public void testList() throws Exception {
		List<Employee> list = service.list();
		for (Employee e : list) {
			System.out.println(e);
		}
	}
}
