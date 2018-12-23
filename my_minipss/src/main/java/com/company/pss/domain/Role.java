package com.company.pss.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {

	private Long id;

	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色代码
	 */
	private String sn;
	/**
	 * 权限
	 */
	private List<Permission> permissions = new ArrayList<Permission>();

	/**
	 * 一个角色可以拥有多个菜单
	 */
	private List<SystemMenu> systemMenus = new ArrayList<SystemMenu>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<SystemMenu> getSystemMenus() {
		return systemMenus;
	}

	public void setSystemMenus(List<SystemMenu> systemMenus) {
		this.systemMenus = systemMenus;
	}

}
