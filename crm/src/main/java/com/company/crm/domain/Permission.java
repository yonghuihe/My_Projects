package com.company.crm.domain;

public class Permission {
	private Long id;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限表达式
	 */
	private String resource;

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
		this.name = name == null ? null : name.trim();
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource == null ? null : resource.trim();
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", resource=" + resource + "]";
	}

}