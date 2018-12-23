package com.xmg.minipss.domain;


public class Department extends BaseDomain {

	private String name;
	private String sn;
	
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

	@Override
	public String toString() {
		return "Department [name=" + name + ", sn=" + sn + "]";
	}

}
