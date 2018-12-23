package com.company.sm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sm.domain.Student;
import com.company.sm.mapper.StudentMapper;
import com.company.sm.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public void add(Student student) {
		studentMapper.add(student);
	}

}
