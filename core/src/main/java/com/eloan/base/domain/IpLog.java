package com.eloan.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

/**
 * 登陆日志
 * @author Administrator
 */
@Getter
@Setter
@Alias("IpLog")
public class IpLog extends BaseDomain {
	private static final long serialVersionUID = 8064136831955832861L;
	public static int LOGINSTATE_FAILED = 0;//登陆失败
	public static int LOGINSTATE_SUCCESS = 1;//登陆成功

	private String username;//登录用户名
	private Date loginTime;//登录时间
	private String ip;//IP地址

	private int loginState;//登录状态
	private int loginType;//用户登录类型
	private Long loginInfoId;//登录用户id
	
	public String getDisplayState(){
		return this.loginState==LOGINSTATE_FAILED?"登录失败":"登录成功";
	}

	public IpLog() {
		super();
	}

	public IpLog(String username, Date loginTime, String ip, int loginType,
			Long loginInfoId) {
		super();
		this.username = username;
		this.loginTime = loginTime;
		this.ip = ip;
		this.loginState = IpLog.LOGINSTATE_FAILED;
		this.loginType = loginType;
		this.loginInfoId = loginInfoId;
	}
}
