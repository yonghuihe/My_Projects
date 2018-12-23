package com._520it.es.service.impl;

import com._520it.es.domain.KeywordReply;
import com._520it.es.mapper.KeywordReplyMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IKeywordReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class KeywordReplyServiceImpl implements IKeywordReplyService {
	@Autowired
	private KeywordReplyMapper keywordReplyMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return keywordReplyMapper.deleteByPrimaryKey(id);
	}

	public int insert(KeywordReply record) {
		return keywordReplyMapper.insert(record);
	}

	public KeywordReply selectByPrimaryKey(Long id) {
		return keywordReplyMapper.selectByPrimaryKey(id);
	}

	public List<KeywordReply> selectAll() {
		return keywordReplyMapper.selectAll();
	}

	public int updateByPrimaryKey(KeywordReply record) {
		return keywordReplyMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = keywordReplyMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<KeywordReply> result = keywordReplyMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public String selectClick() {
		String click = keywordReplyMapper.selectClick();
		return click;
	}

	@Override
	public List<KeywordReply> selectReply() {
		List<KeywordReply> keywordReplies = keywordReplyMapper.selectReply();
		return keywordReplies;
	}
}
