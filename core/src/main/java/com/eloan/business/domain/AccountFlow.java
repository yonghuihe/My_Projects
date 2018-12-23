package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;

import lombok.Getter;
import lombok.Setter;

/**
 * 账户流水
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
public class AccountFlow extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private int accountActionType;// 账户流水类型
	private BigDecimal amount = BidConst.ZERO;// 发生额（账户流水金额）
	private Account account;// 对应账户
	private String note;// 备注
	private BigDecimal balance = BidConst.ZERO;// 可用金额
	private BigDecimal freezed = BidConst.ZERO;// 冻结金额
	private Date actionTime;//发生时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
}
