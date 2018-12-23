package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IChartDao;
import com.company.pss.domain.OrderBillChartVO;
import com.company.pss.domain.SaleAccountChartVO;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IChartService;

public class ChartServiceImpl implements IChartService {
	private IChartDao chartDao;

	public List<OrderBillChartVO> queryOrderBillChart(QueryObject qo) {
		return chartDao.queryOrderBillChart(qo);
	}
	
	public void setChartDao(IChartDao chartDao) {
		this.chartDao = chartDao;
	}

	public List<SaleAccountChartVO> querySaleAccountChart(QueryObject qo) {
		return chartDao.querySaleAccountChart(qo);
	}
}
