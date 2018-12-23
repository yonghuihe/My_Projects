package com.company.ssh.domain;

import java.io.Serializable;

public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 5738967460858353766L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
