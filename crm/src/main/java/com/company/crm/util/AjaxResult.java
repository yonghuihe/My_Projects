package com.company.crm.util;

/**
 * 
 * @author yonghui
 *
 */
public class AjaxResult {
	/**
	 * 提示信息
	 */
	private String msg;
	/**
	 * 是否成功
	 */
	private boolean success;

	public AjaxResult() {
		super();
	}

	public AjaxResult(String msg, boolean success) {
		super();
		this.msg = msg;
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "AjaxResult [msg=" + msg + ", success=" + success + "]";
	}

}
