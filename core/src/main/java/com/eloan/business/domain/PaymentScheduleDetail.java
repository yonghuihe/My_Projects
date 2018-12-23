package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.eloan.base.domain.BaseDomain;
import com.eloan.base.domain.Logininfo;
import com.eloan.business.util.BidConst;

import lombok.Getter;
import lombok.Setter;

/**
 * 还款计划明细：针对投资人，表示投资人的回款明细
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
public class PaymentScheduleDetail extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private BigDecimal bidAmount = BidConst.ZERO; // 该标总共投标金额
	private Long bidId; // 投标对象
	private Long paymentScheduleId; // 所属还款计划
	private Logininfo borrowUser; // 发标人/借款人
	private Long investorId; // 投资人/收款人

	private Long bidRequestId; // 借款对象
	private int returnType; // 还款类型
	private Date payDate; // 还款日期
	private Date deadLine; // 本期还款截止日期
	private int monthIndex; // 第几期还款（第几个月）
	private BigDecimal totalAmount = BidConst.ZERO; // 本期还款金额
	private BigDecimal principal = BidConst.ZERO; // 本期还款本金
	private BigDecimal interest = BidConst.ZERO; // 本期还款利息

}
