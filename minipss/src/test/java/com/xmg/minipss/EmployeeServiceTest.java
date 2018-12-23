package com.xmg.minipss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
public class EmployeeServiceTest {

	@Autowired
	private IEmployeeService service;

	@Test
	public void testSave() {
		Employee e = new Employee();
		service.save(e);
	}

}
