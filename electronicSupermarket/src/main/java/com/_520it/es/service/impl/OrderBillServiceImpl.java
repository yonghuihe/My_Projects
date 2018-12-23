package com._520it.es.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.es.domain.OrderBill;
import com._520it.es.mapper.OrderBillMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IOrderBillService;
@Service
public class OrderBillServiceImpl implements IOrderBillService {
	@Autowired
	private OrderBillMapper orderBillMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return orderBillMapper.deleteByPrimaryKey(id);
	}

	public int insert(OrderBill record) {
		return orderBillMapper.insert(record);
	}

	public OrderBill selectByPrimaryKey(Long id) {
		return orderBillMapper.selectByPrimaryKey(id);
	}

	public List<OrderBill> selectAll() {
		return orderBillMapper.selectAll();
	}

	public int updateByPrimaryKey(OrderBill record) {
		return orderBillMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = orderBillMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<OrderBill> result = orderBillMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
