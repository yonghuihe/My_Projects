package com.company.pss.mvc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {
	private static final long serialVersionUID = -2473255477317460372L;
	
	/**
	 * 定义结果视图
	 */
	public static final String LIST = "list";
	public static final String NO_PERMISSION = "nopermission";
	public static final String PAGERESULT = "result";
	

	public void addContext(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	
	public void addSession(String key,Object value){
		ActionContext.getContext().getSession().put(key, value);
	}

	@Override
	public void prepare() throws Exception {
		
	}
	
}
