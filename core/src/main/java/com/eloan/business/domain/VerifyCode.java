package com.eloan.business.domain;

import java.util.Date;

import com.eloan.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VerifyCode extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private String randomCode;
	private Date lastSendTime;
	private String content;

	public VerifyCode() {
		super();
	}

	public VerifyCode(String phoneNumber, String randomCode, Date lastSendTime,
			String content) {
		super();
		this.phoneNumber = phoneNumber;
		this.randomCode = randomCode;
		this.lastSendTime = lastSendTime;
		this.content = content;
	}

}
