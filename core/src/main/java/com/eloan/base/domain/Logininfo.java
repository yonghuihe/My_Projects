package com.eloan.base.domain;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("Logininfo")
public class Logininfo extends BaseDomain {

	private static final long serialVersionUID = 1L;
	public static final int STATE_NORMAL = 0;//用户登录状态
	public static final int STATE_LOCK = 1;//用户锁定状态
	public static final int STATE_DELETE = -1;//用户注销状态

	public static final int USERTYPE_NORMAL = 0;// 前段用户
	public static final int USERTYPE_SYSTEM = 1;// 后台用户

	private String username;
	private String password;
	private int state = STATE_NORMAL;

	private int userType;// 用户类型
	private boolean admin = false;

}
