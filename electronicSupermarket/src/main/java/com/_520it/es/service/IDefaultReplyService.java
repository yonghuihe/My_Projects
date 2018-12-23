package com._520it.es.service;
import java.util.List;
import com._520it.es.domain.DefaultReply;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

public interface IDefaultReplyService {
	int deleteByPrimaryKey(Long id);
    int insert(DefaultReply record);
    DefaultReply selectByPrimaryKey(Long id);
    List<DefaultReply> selectAll();
    int updateByPrimaryKey(DefaultReply record);
	PageResult queryByConditionPage(QueryObject qo);
}
