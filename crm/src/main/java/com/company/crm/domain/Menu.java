package com.company.crm.domain;

import java.util.List;

import com.company.crm.genertor.ObjectProp;

public class Menu {
	private Long id;
	@ObjectProp("菜单名称")
	private String text;
	@ObjectProp("菜单url")
	private String url;
	@ObjectProp("父菜单")
	private Menu parent;
	@ObjectProp("子菜单")
	private List<Menu> children;
	// 菜单关联权限
	private Permission permission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", text=" + text + ", url=" + url + ", parent=" + parent + ", children=" + children
				+ ", permission=" + permission + "]";
	}

}
