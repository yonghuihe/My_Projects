package com.company.sm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.sm.domain.Student;
import com.company.sm.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentMapperTest {
	
	@Autowired
	private IStudentService studentService;

	@Test
	public void testAdd() {
		
		Student student = new Student();
		student.setName("ss");
		
		//保存学生
		studentService.add(student);
		
	}
	
	
}
