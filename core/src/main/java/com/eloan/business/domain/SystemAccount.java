package com.eloan.business.domain;

import java.math.BigDecimal;

import com.eloan.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统账户
 * @author yonghui
 *
 */
@Setter
@Getter
public class SystemAccount extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	private int version;
	private BigDecimal usableAmount;
	private BigDecimal freezedAmount;

}
