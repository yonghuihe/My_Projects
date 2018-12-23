package com.company.pss.query;

import java.util.Date;

import com.company.pss.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderBillQueryObject extends QueryObject {

	private Date beginDate;
	private Date endDate;
	private Long supplierId = -1L;
	private int status = -1;

	@Override
	protected void customerQuery() {
		if (beginDate != null) {
			this.addCondition("(obj.vdate >= ?)", DateUtil.beginDateTime(beginDate));
		}
		if (endDate != null) {
			this.addCondition("(obj.vdate <= ?)", DateUtil.endDateTime(endDate));
		}
		if (supplierId != null && supplierId > 0) {
			this.addCondition("obj.supplier.id = ?", supplierId);
		}
		if (status > -1) {
			this.addCondition("obj.status = ?", status);
		}
	}
}
