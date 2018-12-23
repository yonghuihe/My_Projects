package com.eloan.base.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Long id;

}
