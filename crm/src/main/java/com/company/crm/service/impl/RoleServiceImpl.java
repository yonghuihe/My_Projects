package com.company.crm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.BatchInsertIds;
import com.company.crm.domain.Permission;
import com.company.crm.domain.Role;
import com.company.crm.mapper.RoleMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	public int deleteByPrimaryKey(Long id) {
		// 打破关系
		roleMapper.deleteRelation(id);
		return roleMapper.deleteByPrimaryKey(id);
	}

	public int insert(Role record) {
		// 要先保存角色，再维护中间表，保证角色id不为null
		int insert = roleMapper.insert(record);

		List<Permission> permissions = record.getPermissions();
		if (permissions.size() > 0) {
			List<BatchInsertIds> list = new ArrayList<BatchInsertIds>();
			BatchInsertIds ids = null;
			// 维护中间表
			for (Permission permission : permissions) {
				ids = new BatchInsertIds(record.getId(), permission.getId());
				list.add(ids);
				// 单个插入（如果权限较多，也需要发送多条SQL）
				// roleMapper.insertRelation(record.getId(),
				// permission.getId());
			}
			// 批量插入（只发送一条SQL）
			roleMapper.batchInsertRelation(list);
		}
		return insert;
	}

	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	public PageResult queryPageResult(QueryObject qo) {
		// 判断结果总数
		Long count = roleMapper.queryPageResultCount(qo);
		if (count > 0) {
			List<Role> result = roleMapper.queryPageResult(qo);
			return new PageResult(count, result);
		}
		return new PageResult(count, Collections.EMPTY_LIST);
	}

	public int updateByPrimaryKey(Role record) {
		// 打破关系
		roleMapper.deleteRelation(record.getId());

		List<Permission> permissions = record.getPermissions();
		System.out.println(permissions.size());
		if (permissions.size() > 0) {
			List<BatchInsertIds> list = new ArrayList<BatchInsertIds>();
			BatchInsertIds ids = null;
			// 维护中间表
			for (Permission permission : permissions) {
				ids = new BatchInsertIds(record.getId(), permission.getId());
				list.add(ids);
			}
			// 批量插入（只发送一条SQL）
			roleMapper.batchInsertRelation(list);
		}

		return roleMapper.updateByPrimaryKey(record);
	}

}
