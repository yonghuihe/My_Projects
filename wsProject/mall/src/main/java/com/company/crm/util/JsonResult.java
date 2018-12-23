package com.company.crm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
	private boolean success = false;
	private String msg;
	public JsonResult(String msg) {
		super();
		this.msg = msg;
	}
}
