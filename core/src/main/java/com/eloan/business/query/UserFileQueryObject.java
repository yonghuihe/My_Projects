package com.eloan.business.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFileQueryObject extends BaseAuditQueryObject {
	
	/**
	 * 借款人的查询条件
	 */
	private Long applierId;
}
