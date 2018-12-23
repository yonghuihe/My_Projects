package com.company.pss.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 按照父菜单id查询
 * @author yonghui
 *
 */
public class SystemMenuQueryObject extends QueryObject {
	
	@Getter@Setter
	private Long parentId;
	
	@Getter@Setter
	private String sn;

	/**
	 * 如果没有查询到parentId，查询根目录
	 */
	@Override
	protected void customerQuery() {
		if(parentId != null){
			this.addCondition("obj.parent.id = ?", parentId);
		} else {
			this.addCondition("obj.parent is null");
		}
	}
}
