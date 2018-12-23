package com.eloan.business.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class BidRequestAuditHistoryQueryObject extends BaseAuditQueryObject {
	
	private int auditType = -1;
	private Long bidRequestId;
}
