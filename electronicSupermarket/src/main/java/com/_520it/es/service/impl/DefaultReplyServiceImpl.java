package com._520it.es.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.es.domain.DefaultReply;
import com._520it.es.mapper.DefaultReplyMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IDefaultReplyService;
@Service
public class DefaultReplyServiceImpl implements IDefaultReplyService {
	@Autowired
	private DefaultReplyMapper defaultReplyMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return defaultReplyMapper.deleteByPrimaryKey(id);
	}

	public int insert(DefaultReply record) {
		return defaultReplyMapper.insert(record);
	}

	public DefaultReply selectByPrimaryKey(Long id) {
		return defaultReplyMapper.selectByPrimaryKey(id);
	}

	public List<DefaultReply> selectAll() {
		return defaultReplyMapper.selectAll();
	}

	public int updateByPrimaryKey(DefaultReply record) {
		return defaultReplyMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = defaultReplyMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<DefaultReply> result = defaultReplyMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
