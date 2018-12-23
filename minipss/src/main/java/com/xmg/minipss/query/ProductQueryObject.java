package com.xmg.minipss.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject {

	private long brandId = -1;

	@Override
	public void customerQuery() {
		if (brandId > 0) {
			this.addCondition("obj.brand.id = ?", brandId);
		}
	}

}
