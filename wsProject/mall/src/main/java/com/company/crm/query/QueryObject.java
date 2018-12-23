package com.company.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
	private Integer page = 1;//当前页
	private Integer rows = 10;//每页显示的记录数
	
	public Integer getStart(){
		return (page-1)*rows;
	}
}
