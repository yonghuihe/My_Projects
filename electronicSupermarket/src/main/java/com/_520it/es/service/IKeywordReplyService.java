package com._520it.es.service;
import com._520it.es.domain.KeywordReply;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface IKeywordReplyService {
	int deleteByPrimaryKey(Long id);
    int insert(KeywordReply record);
    KeywordReply selectByPrimaryKey(Long id);
    List<KeywordReply> selectAll();
    int updateByPrimaryKey(KeywordReply record);
	PageResult queryByConditionPage(QueryObject qo);
    String selectClick();
    List<KeywordReply> selectReply();
}
