package com.company.pss.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.company.pss.domain.OrderBillGroupBy;
import com.company.pss.domain.SaleAccountChartVO;
import com.company.pss.domain.SaleAccountGroupBy;
import com.company.pss.query.ChartQueryObject;
import com.company.pss.query.SaleAccountChartQueryObject;
import com.company.pss.service.IBrandService;
import com.company.pss.service.IChartService;
import com.company.pss.service.IClientService;
import com.company.pss.service.ISupplierService;
import com.company.pss.util.RequiredPermission;

public class ChartAction extends BaseAction {

	private static final long serialVersionUID = -143785592454387039L;
	
	private IChartService chartService;
	private IBrandService brandService;
	private ISupplierService supplierService;
	private IClientService clientService;
	private ChartQueryObject queryObject = new ChartQueryObject();
	private SaleAccountChartQueryObject sqo = new SaleAccountChartQueryObject();
	
	@RequiredPermission("Chart结果集")
	public String orderBillChart() throws Exception {
		this.addContext("groupByValues", OrderBillGroupBy.values());
		//将查询到的结果集放到context区域
		this.addContext(PAGERESULT, this.chartService.queryOrderBillChart(queryObject));
		this.addContext("brands", this.brandService.list());
		this.addContext("suppliers", this.supplierService.list());
		
		return "orderBillChart";
	}
	
	public String saleAccountChart() throws Exception {
		this.addContext("groupByValues", SaleAccountGroupBy.values());
		//将查询到的结果集放到context区域
		this.addContext(PAGERESULT, this.chartService.querySaleAccountChart(sqo));
		this.addContext("brands", this.brandService.list());
		this.addContext("clients", this.clientService.list());
		
		return "saleAccountChart";
	}
	
	public String saleAccountChartByLine(){
		List<SaleAccountChartVO> results = this.chartService.querySaleAccountChart(sqo);
		List<String> groupByNames = new ArrayList<String>();
		List<BigDecimal> totalAmount = new ArrayList<BigDecimal>();
		for (SaleAccountChartVO sa : results) {
			groupByNames.add(sa.getGroupByName());
			totalAmount.add(sa.getTotalAmount());
		}
		SaleAccountGroupBy saGroupBy = SaleAccountGroupBy.valueOf(sqo.getGroupType());
		
		this.addContext("groupByValue", saGroupBy.getValue());
		this.addContext("groupByNames", JSON.toJSONString(groupByNames));
		this.addContext("totalAmount", JSON.toJSONString(totalAmount));
		return "saleAccountChartByLine";
	}
	
	public String saleAccountChartByPie(){
		List<SaleAccountChartVO> results = this.chartService.querySaleAccountChart(sqo);
		List<Object[]> datas = new ArrayList<Object[]>();
		for (SaleAccountChartVO sa : results) {
			datas.add(new Object[]{sa.getGroupByName(),sa.getTotalAmount()});
		}
		SaleAccountGroupBy saGroupBy = SaleAccountGroupBy.valueOf(sqo.getGroupType());
		
		this.addContext("groupByValue", saGroupBy.getValue());
		this.addContext("datas", JSON.toJSONString(datas));
		return "saleAccountChartByPie";
	}

	public ChartQueryObject getQueryObject() {
		return queryObject;
	}

	public SaleAccountChartQueryObject getSqo() {
		return sqo;
	}

	public void setChartService(IChartService chartService) {
		this.chartService = chartService;
	}
	
	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}
	
	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
}
