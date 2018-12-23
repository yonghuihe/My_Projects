package com.company.pss.domain;

/**
 * 仓库
 * 
 * @author yonghui
 *
 */
public class Depot {

	private Long id;
	/**
	 * 仓库名称
	 */
	private String name;
	/**
	 * 仓库地址
	 */
	private String location;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Depot [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

}
