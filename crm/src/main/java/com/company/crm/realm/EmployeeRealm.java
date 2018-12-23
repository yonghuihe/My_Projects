package com.company.crm.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.crm.domain.Employee;
import com.company.crm.service.IEmployeeService;

public class EmployeeRealm extends AuthorizingRealm {

	@Autowired
	private IEmployeeService employeeService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权验证");
		// 1、获取主体对象
		Employee employee = (Employee) principals.getPrimaryPrincipal();

		List<String> permissions = new ArrayList<String>();
		List<String> roles = new ArrayList<String>();

		// 如果是超级管理员就不查询数据库了
		if (employee.getAdmin()) {
			roles.add("admin");
			permissions.add("*:*");
		} else {
			// 根据用户去查询拥有的角色和权限
			roles = employeeService.getRoleByEId(employee.getId());
			permissions = employeeService.getPermissionByEId(employee.getId());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);
		info.addStringPermissions(permissions);
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证");
		// 获取表单提交的用户名
		String username = (String) token.getPrincipal();
		// 查询数据库是否有该用户名，如果没有返回null，否则返回info对象
		Employee employee = employeeService.getEmployeeByUsername(username);
		if (employee == null) {
			return null;
		}
		// 告诉shiro：盐
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(),
				ByteSource.Util.bytes(employee.getUsername()), this.getName());
		return info;
	}

}
