package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 记录一次提现申请的记录
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
public class MoneyWithDraw extends BaseAuditDomain {

	private static final long serialVersionUID = 1L;

	private BigDecimal amount;// 提现金额
	private BigDecimal chargeFee;// 提现手续费
	private String bankName;// 银行名称
	private String bankNumber;// 银行账户
	private String accountName;// 开户人姓名
	private String forkName;// 支行名称
	
	public String getJsonString(){
		Map<String,Object> map = new HashMap<>();
		map.put("id", super.id);
		map.put("username", super.getApplier().getUsername());
		map.put("realName", this.accountName);
		map.put("applyTime", super.getApplyTime());
		map.put("bankName", this.bankName);
		map.put("bankNumber", this.bankNumber);
		map.put("forkName", this.forkName);
		map.put("amount", this.amount);
		return JSONObject.toJSONString(map);
	}

}
