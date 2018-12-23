package com.company.crm.service.impl;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Employee;
import com.company.crm.domain.Permission;
import com.company.crm.mapper.PermissionMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.PermissionQueryObject;
import com.company.crm.service.IPermissionService;
import com.company.crm.util.PermissionName;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private ApplicationContext ac;

	public int deleteByPrimaryKey(Long id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	public int insert(Permission record) {
		return permissionMapper.insert(record);
	}

	public Permission selectByPrimaryKey(Long id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	public List<Permission> selectAll() {
		return permissionMapper.selectAll();
	}

	public int updateByPrimaryKey(Permission record) {
		return permissionMapper.updateByPrimaryKey(record);
	}

	public List<Permission> getPermissionsByRId(Long rId) {
		return permissionMapper.getPermissionsByRId(rId);
	}

	public PageResult queryPageResult(PermissionQueryObject qo) {
		// 判断结果总数
		Long count = permissionMapper.queryPageResultCount(qo);
		if (count > 0) {
			List<Employee> result = permissionMapper.queryPageResult(qo);
			return new PageResult(count, result);
		}
		return new PageResult(count, Collections.EMPTY_LIST);
	}

	public void load() {
		// 0、查询出所有权限表达式
		List<String> resources = permissionMapper.selectAllResource();
		// 1、获取拥有Controller注解的所有bean
		Map<String, Object> map = ac.getBeansWithAnnotation(Controller.class);
		// 2、遍历集合的values方法，获得controller类
		for (Object controller : map.values()) {
			// 3、获取controller类上所有声明的方法
			Method[] methods = controller.getClass().getDeclaredMethods();
			// 4、遍历方法数组，得到每一个方法
			for (Method method : methods) {
				// 5、获取方法上的@RequirePermission注解
				RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
				// 6、判断该方法上是否有该注解，如果没有结束本次循环（内层循环），执行下一此循环（外层循环）
				if (annotation == null) {
					continue;
				}
				// 7、获取该方法上的注解上的值:权限表达式
				String[] value = annotation.value();
				// 8、将String[]转换成String
				String resource = StringUtils.join(value);
				// 9、判断该表达式是否已经存在于数据库中，如果存在结束循环，不存在插入到数据库
				if (resources.contains(resource)) {
					continue;
				}
				// 10、获取方法上的@PermissionName注解
				PermissionName permissionName = method.getAnnotation(PermissionName.class);
				// 11、创建permission对象，将权限名称、权限表达式封装到permission对象中
				Permission permission = new Permission();
				permission.setResource(resource);
				permission.setName(permissionName.value());
				// 12、插入到数据库中
				permissionMapper.insert(permission);
			}
		}
	}

}
