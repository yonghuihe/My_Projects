package com.company.pss.domain;

public class Brand {

	private Long id;
	/**
	 * 品牌名称
	 */
	private String name;

	/**
	 * 品牌编码
	 */
	private String sn;

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

}
