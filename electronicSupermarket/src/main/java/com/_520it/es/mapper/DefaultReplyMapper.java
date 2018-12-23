package com._520it.es.mapper;

import com._520it.es.domain.DefaultReply;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface DefaultReplyMapper {
    int deleteByPrimaryKey(Long id);
    int insert(DefaultReply record);
    DefaultReply selectByPrimaryKey(Long id);
    List<DefaultReply> selectAll();
    int updateByPrimaryKey(DefaultReply record);
	Long queryByCondictionCount(QueryObject qo);
	List<DefaultReply> queryByConditionResult(QueryObject qo);
}