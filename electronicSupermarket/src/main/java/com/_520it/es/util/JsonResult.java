package com._520it.es.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by ydm on 2017/7/4.
 */

@NoArgsConstructor
@Setter
@Getter
public class JsonResult {
    private boolean success;
    private String  msg;
    public JsonResult(String msg){
        this.msg = msg;
    }
	public JsonResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
    
    
}
