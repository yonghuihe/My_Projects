package com.eloan.business.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 还款计划查询对象
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
public class PaymentScheduleQueryObject extends BaseAuditQueryObject {
	private Long userinfoId;// 当前登录的用户
}
