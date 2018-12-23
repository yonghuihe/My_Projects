package com.company.pss.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

public class SystemMenu {

	private Long id;
	private String name;// 菜单名称
	private String url;// 点击链接位置
	private String sn;// 菜单编码
	private SystemMenu parent;// 父菜单
	private List<SystemMenu> children = new ArrayList<SystemMenu>();

	/**
	 * 用于显示菜单的父级菜单名称
	 * 
	 * @return
	 */
	@JSON(serialize = false)
	public String getParentDisPlayName() {
		if (parent != null) {
			return parent.getId() == null ? "父菜单" : parent.getName();
		}
		return "父菜单";
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("action", this.url);
		return map;
	}

	/**
	 * 迭代出当前菜单的所有父级菜单
	 * 
	 * @return
	 */
	@JSON(serialize = false)
	public List<SystemMenu> getAllParent() {
		List<SystemMenu> menus = new ArrayList<SystemMenu>();
		if (this.getId() != null) {
			menus.add(this);
			if (this.parent != null) {
				iteratorParent(this.parent, menus);
			}
			// 如果超过两层菜单，做一个逆序
			if (menus.size() > 0) {
				Collections.reverse(menus);
			}
		}
		return menus;
	}

	private void iteratorParent(SystemMenu menu, List<SystemMenu> menus) {
		if (this.parent != null) {
			menus.add(menu);
			iteratorParent(menu.getParent(), menus);
		}
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public SystemMenu getParent() {
		return parent;
	}

	public void setParent(SystemMenu parent) {
		this.parent = parent;
	}

	public List<SystemMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SystemMenu> children) {
		this.children = children;
	}

}
