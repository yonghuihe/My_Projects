package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.OrderBillChartVO;
import com.company.pss.domain.SaleAccountChartVO;
import com.company.pss.query.QueryObject;

public interface IChartService {
	List<OrderBillChartVO> queryOrderBillChart(QueryObject qo);

	List<SaleAccountChartVO> querySaleAccountChart(QueryObject qo);
}
