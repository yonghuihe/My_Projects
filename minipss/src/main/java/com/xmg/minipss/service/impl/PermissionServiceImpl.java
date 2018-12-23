package com.xmg.minipss.service.impl;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.xmg.minipss.dao.IPermissionDAO;
import com.xmg.minipss.domain.Permission;
import com.xmg.minipss.mvc.BaseAction;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IPermissionService;
import com.xmg.minipss.util.PermissionUtil;
import com.xmg.minipss.util.RequiredPermission;

public class PermissionServiceImpl implements IPermissionService,
		ApplicationContextAware {

	private IPermissionDAO permissionDAO;
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

	public void setPermissionDAO(IPermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	@Override
	public void delete(Permission o) {
		this.permissionDAO.delete(o);
	}

	@Override
	public List<Permission> list() {
		return this.permissionDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.permissionDAO.query(qo);
	}

	@Override
	public void reload() {
		//首先得到容器中所有的Action
		Map<String, BaseAction> actions = ctx.getBeansOfType(BaseAction.class);
		//先得到系统已有的所有权限表达式
		Set<String> expressions = new HashSet<String>();
		List<Permission> ps = this.list();
		for (Permission p : ps) {
			expressions.add(p.getExpression());
		}

		//遍历这些action,得到打了标签的方法
		for (Entry<String, BaseAction> entry : actions.entrySet()) {
			Class<?> actionClass = entry.getValue().getClass();
			Method[] methods = actionClass.getDeclaredMethods();

			for (Method m : methods) {
				String expression = PermissionUtil.createExpression(m);
				if (!expressions.contains(expression)) {
					if (m.isAnnotationPresent(RequiredPermission.class)) {
						RequiredPermission rp = m
								.getAnnotation(RequiredPermission.class);
						Permission p = new Permission();
						p.setName(rp.value());
						p.setExpression(expression);
						this.permissionDAO.save(p);
					}
				}
			}
		}
	}

}
