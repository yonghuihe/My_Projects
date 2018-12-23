package com.company.ssh.domain;

import java.util.ArrayList;
import java.util.List;

public class Role extends BaseDomain {
	private static final long serialVersionUID = 1L;
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

	@Override
	public String toString() {
		return "Role [name=" + name + ", sn=" + sn + ", permissions=" + permissions + "]";
	}

}
