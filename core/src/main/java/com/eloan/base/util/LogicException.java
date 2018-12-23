package com.eloan.base.util;

public class LogicException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 异常错误代码，类似于 HTTP 响应的状态码， 比如 404 代表（页面没找到）， 101 代表用户名不存在，102 代表密码错误
	 */
	private Integer errCode;

	public Integer getErrCode() {
		return errCode;
	}

	public LogicException(String message) {
		super(message);
	}

	public LogicException(String message, Integer errCode) {
		super(message);
		this.errCode = errCode;
	}
}
