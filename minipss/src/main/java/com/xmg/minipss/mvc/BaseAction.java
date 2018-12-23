package com.xmg.minipss.mvc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {

	public static final String LIST = "list";
	public static final String NOPERMISSION = "nopermission";
	public static final String PAGERESULT = "result";
	public static final String JSON = "jsonResult";

	public void addContext(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	public void addSession(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	@Override
	public void prepare() throws Exception {

	}
}
