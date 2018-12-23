package com.xmg.minipss.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemMenu extends BaseDomain {

	private String name;
	private String action;
	private String sn;
	private SystemMenu parent;

	public String getParentDisplay() {
		return parent == null ? "根目录" : parent.getName();
	}

	public List<SystemMenu> getAllParents() {
		List<SystemMenu> all = new ArrayList<SystemMenu>();
		all.add(this);
		this.iteratorParent(all, this.getParent());
		Collections.reverse(all);
		return all;
	}

	private void iteratorParent(List<SystemMenu> all, SystemMenu parent) {
		if (parent != null) {
			all.add(parent);
			iteratorParent(all, parent.getParent());
		}
	}

	/**
	 * 把对象转成json的map
	 * @return
	 */
	public Map<String, Object> toJson() {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("id", this.getId());
		json.put("pId", this.getParent() == null ? 0L : this.getParent()
				.getId());
		json.put("name", this.getName());
		json.put("action", this.getAction());
		return json;
	}

}
