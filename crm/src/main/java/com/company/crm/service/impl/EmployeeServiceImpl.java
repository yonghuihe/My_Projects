package com.company.crm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.BatchInsertIds;
import com.company.crm.domain.Employee;
import com.company.crm.domain.Role;
import com.company.crm.mapper.EmployeeMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.EmployeeQueryObject;
import com.company.crm.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	public int deleteByPrimaryKey(Long id) {
		// 打破关系
		employeeMapper.deleteRelation(id);

		return employeeMapper.deleteByPrimaryKey(id);
	}

	public int insert(Employee record) {
		//密码加密操作
		String password = record.getPassword();
		String username = record.getUsername();
		password = new Md5Hash(password, username, 2).toString();
		record.setPassword(password);
		
		// 新添加的员工默认设置为：在职
		record.setState(true);

		int insert = employeeMapper.insert(record);

		// 维护关系：员工与角色的关系
		List<Role> roles = record.getRoles();
		List<BatchInsertIds> list = new ArrayList<BatchInsertIds>();
		if (roles.size() > 0) {
			BatchInsertIds ids = null;
			for (Role role : roles) {
				ids = new BatchInsertIds(record.getId(), role.getId());
				list.add(ids);
			}
			employeeMapper.batchInsertRelation(list);
		}

		return insert;
	}

	public Employee selectByPrimaryKey(Long id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	public List<Employee> selectAll() {
		return employeeMapper.selectAll();
	}

	public int updateByPrimaryKey(Employee record) {

		// 打破关系
		employeeMapper.deleteRelation(record.getId());

		// 维护关系：员工与角色的关系
		List<Role> roles = record.getRoles();
		List<BatchInsertIds> list = new ArrayList<BatchInsertIds>();
		if (roles.size() > 0) {
			BatchInsertIds ids = null;
			for (Role role : roles) {
				ids = new BatchInsertIds(record.getId(), role.getId());
				list.add(ids);
			}
			employeeMapper.batchInsertRelation(list);
		}

		return employeeMapper.updateByPrimaryKey(record);
	}

	public PageResult queryPageResult(EmployeeQueryObject qo) {
		// 判断结果总数
		Long count = employeeMapper.queryPageResultCount(qo);
		if (count > 0) {
			List<Employee> result = employeeMapper.queryPageResult(qo);
			return new PageResult(count, result);
		}
		return new PageResult(count, Collections.EMPTY_LIST);
	}

	public void updateState(Long id) {
		// 打破关系
		employeeMapper.deleteRelation(id);

		Employee employee = new Employee();
		employee.setId(id);
		employee.setState(false);
		employeeMapper.updateState(employee);

	}

	public List<Long> getRoleIdsByEId(Long id) {
		List<Long> list = employeeMapper.getRoleIdsByEId(id);
		System.out.println(list);
		return list;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		return employeeMapper.getEmployeeByUsername(username);
	}

	@Override
	public List<String> getRoleByEId(Long id) {
		return employeeMapper.getRoleByEId(id);
	}

	@Override
	public List<String> getPermissionByEId(Long id) {
		return employeeMapper.getPermissionByEId(id);
	}

}
