package com.xmg.minipss.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseDomain {

	private String name;
	private String sn;
	private List<Permission> permissions = new ArrayList<Permission>();
	private List<SystemMenu> menus=new ArrayList<SystemMenu>();

}
