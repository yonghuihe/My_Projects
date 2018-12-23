package com._520it.es.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.es.domain.OrderBillItem;
import com._520it.es.mapper.OrderBillItemMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IOrderBillItemService;
@Service
public class OrderBillItemServiceImpl implements IOrderBillItemService {
	@Autowired
	private OrderBillItemMapper orderBillItemMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return orderBillItemMapper.deleteByPrimaryKey(id);
	}

	public int insert(OrderBillItem record) {
		return orderBillItemMapper.insert(record);
	}

	public OrderBillItem selectByPrimaryKey(Long id) {
		return orderBillItemMapper.selectByPrimaryKey(id);
	}

	public List<OrderBillItem> selectAll() {
		return orderBillItemMapper.selectAll();
	}

	public int updateByPrimaryKey(OrderBillItem record) {
		return orderBillItemMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = orderBillItemMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<OrderBillItem> result = orderBillItemMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
