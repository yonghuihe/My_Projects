package com.xmg.minipss.domain;

import java.io.Serializable;

public class BaseDomain implements Serializable {

	private static final long serialVersionUID = -5998920029409507266L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
