package com.xmg.minipss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.xmg.minipss.dao.IEmployeeDAO;
import com.xmg.minipss.domain.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=false)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeDAOTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private IEmployeeDAO dao;

	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setName("name");
		dao.save(e);
	}

}
