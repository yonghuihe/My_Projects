package com.eloan.business.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 线下充值模型
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
@Alias("RechargeOffline")
public class RechargeOffline extends BaseAuditDomain {
	private static final long serialVersionUID = 1L;

	private CompanyBankInfo bankInfo;// 平台账号
	private String tradeCode;// 交易号
	private Date tradeTime;// 交易时间
	private BigDecimal amount;// 交易金额
	private String note;// 备注

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	public String getJsonString(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("username", this.getApplier().getUsername());
		map.put("tradeCode", tradeCode);
		map.put("amount", amount);
		map.put("tradeTime", DateFormat.getDateTimeInstance().format(this.getApplyTime()));
		return JSONObject.toJSONString(map);
	}

}
