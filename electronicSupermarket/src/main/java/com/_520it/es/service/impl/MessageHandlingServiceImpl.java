package com._520it.es.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.es.domain.MessageHandling;
import com._520it.es.mapper.MessageHandlingMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IMessageHandlingService;
@Service
public class MessageHandlingServiceImpl implements IMessageHandlingService {
	@Autowired
	private MessageHandlingMapper messageHandlingMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return messageHandlingMapper.deleteByPrimaryKey(id);
	}

	public int insert(MessageHandling record) {
		return messageHandlingMapper.insert(record);
	}

	public MessageHandling selectByPrimaryKey(Long id) {
		return messageHandlingMapper.selectByPrimaryKey(id);
	}

	public List<MessageHandling> selectAll() {
		return messageHandlingMapper.selectAll();
	}

	public int updateByPrimaryKey(MessageHandling record) {
		return messageHandlingMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = messageHandlingMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<MessageHandling> result = messageHandlingMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
