package com._520it.es.mapper;

import com._520it.es.domain.KeywordReply;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface KeywordReplyMapper {
    int deleteByPrimaryKey(Long id);
    int insert(KeywordReply record);
    KeywordReply selectByPrimaryKey(Long id);
    List<KeywordReply> selectAll();
    int updateByPrimaryKey(KeywordReply record);
	Long queryByCondictionCount(QueryObject qo);
	List<KeywordReply> queryByConditionResult(QueryObject qo);
	String selectClick();
    List<KeywordReply> selectReply();
}