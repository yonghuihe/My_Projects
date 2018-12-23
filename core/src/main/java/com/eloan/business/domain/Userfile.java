package com.eloan.business.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.JSONObject;
import com.eloan.base.domain.SystemDictionaryItem;

/**
 * 风控材料
 */
@Getter
@Setter
public class Userfile extends BaseAuditDomain {
	private static final long serialVersionUID = 1L;
	private String file;//文件地址
	private SystemDictionaryItem fileType;//文件类型
	private int score;//分数

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("username", this.getApplier().getUsername());
		m.put("fileType", fileType.getTitle());
		m.put("file", file);
		return JSONObject.toJSONString(m);
	}
}
