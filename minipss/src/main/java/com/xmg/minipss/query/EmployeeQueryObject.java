package com.xmg.minipss.query;

import org.springframework.util.StringUtils;

public class EmployeeQueryObject extends QueryObject {

	private String keyword;
	private Long deptId = -1L;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	public void customerQuery() {
		if (StringUtils.hasLength(keyword)) {
			this.addCondition("(obj.name LIKE ? or obj.email LIKE ?)", "%"
					+ keyword + "%", "%" + keyword + "%");
		}
		if (deptId > 0) {
			this.addCondition("obj.dept.id = ?", this.deptId);
		}
	}

}
