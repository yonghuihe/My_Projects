package com.eloan.business.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;
import com.eloan.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 平台账户管理
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
@Alias("CompanyBankInfo")
public class CompanyBankInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String bankname;// 银行名称
	private String banknumber;// 银行账户
	private String accountname;// 开户行姓名
	private String bankforkname;// 支行名称
	private String iconCls;// 图标

	public String getJsonString(){
		Map<String,Object> map = new HashMap<>();
		map.put("id", getId());
		map.put("bankname", bankname);
		map.put("accountname", accountname);
		map.put("banknumber", banknumber);
		map.put("bankforkname", bankforkname);
		map.put("iconCls", iconCls);
		return JSONObject.toJSONString(map);
	}
}
