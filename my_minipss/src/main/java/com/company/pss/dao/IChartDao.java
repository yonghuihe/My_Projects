package com.company.pss.dao;

import java.util.List;

import com.company.pss.domain.OrderBillChartVO;
import com.company.pss.domain.SaleAccountChartVO;
import com.company.pss.query.QueryObject;

public interface IChartDao{
	List<OrderBillChartVO> queryOrderBillChart(QueryObject qo);
	
	List<SaleAccountChartVO> querySaleAccountChart(QueryObject qo);
	
}
