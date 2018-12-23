package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.eloan.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统账户流水
 * @author yonghui
 *
 */
@Setter
@Getter
public class SystemAccountFlow extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private Date actionTime;//流水时间
	private int actionType;//流水类型
	private BigDecimal amount;//流水变化的金额
	private String note;//备注
	private BigDecimal usableAmount;//变化之后的可用金额
	private BigDecimal freezedAmount;//变化之后的冻结金额

}
