package com.company.crm.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
	private Long id;
	private String sn;
	private String name;

	private List<Permission> permissions = new ArrayList<Permission>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", sn=" + sn + ", name=" + name + ", permissions=" + permissions + "]";
	}

}