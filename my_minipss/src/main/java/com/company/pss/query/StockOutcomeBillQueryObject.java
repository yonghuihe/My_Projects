package com.company.pss.query;

import java.util.Date;

import com.company.pss.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockOutcomeBillQueryObject extends QueryObject {

	private Date beginDate;
	private Date endDate;
	private Long depotId = -1L;
	private Long clientId = -1L;
	private int status = -1;

	@Override
	protected void customerQuery() {
		if (beginDate != null) {
			this.addCondition("(obj.vdate >= ?)", DateUtil.beginDateTime(beginDate));
		}
		if (endDate != null) {
			this.addCondition("(obj.vdate <= ?)", DateUtil.endDateTime(endDate));
		}
		if (depotId != null && depotId > 0) {
			this.addCondition("obj.depot.id = ?", depotId);
		}
		if (clientId != null && clientId > 0) {
			this.addCondition("obj.client.id = ?", clientId);
		}
		if (status > -1) {
			this.addCondition("obj.status = ?", status);
		}
	}
}
