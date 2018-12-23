package com.xmg.minipss.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject {

	private long parentId = -1L;
	private String parentSn = "";

	@Override
	public void customerQuery() {
		if (parentId > 0) {
			this.addCondition("obj.parent.id = ?", parentId);
		} else {
			this.addCondition("obj.parent IS NULL");
		}
	}

}
