package com.company.pss.service.impl;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.company.pss.dao.IPermissionDao;
import com.company.pss.domain.Permission;
import com.company.pss.mvc.BaseAction;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IPermissionService;
import com.company.pss.util.PermissionUtil;
import com.company.pss.util.RequiredPermission;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
	private IPermissionDao PermissionDao;
	private ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	public void setPermissionDao(IPermissionDao PermissionDao) {
		this.PermissionDao = PermissionDao;
	}

	public void delete(Permission e) {
		PermissionDao.delete(e);
	}

	public List<Permission> list() {
		return PermissionDao.list();
	}
	

	public PageResult query(QueryObject qo) {
		return PermissionDao.query(qo);
	}

	public void reload() {
		// 获取容器中所有的Action
		Map<String, BaseAction> actions = ctx.getBeansOfType(BaseAction.class);
		Set<String> expressions = new HashSet<>(); 
		//先得到系统中所有的标签
		List<Permission> permissions = this.list();
		for (Permission p : permissions) {
			expressions.add(p.getExpression());
		}
		// 遍历这些action，得到打了标签的方法
		for (Entry<String, BaseAction> entry : actions.entrySet()) {
			Class<? extends BaseAction> actionClass = entry.getValue().getClass();
			Method[] methods = actionClass.getDeclaredMethods();
			for (Method method : methods) {
				String expression = PermissionUtil.createExpression(method);
				if(!expressions.contains(expression)){
					if(method.isAnnotationPresent(RequiredPermission.class)){
						RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
						Permission p = new Permission();
						p.setName(rp.value());
						p.setExpression(expression);
						this.PermissionDao.save(p);
					}
				}
			}
		}
	}

}
