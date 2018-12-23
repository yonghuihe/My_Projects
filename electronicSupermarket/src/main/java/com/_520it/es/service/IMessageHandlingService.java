package com._520it.es.service;
import java.util.List;
import com._520it.es.domain.MessageHandling;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

public interface IMessageHandlingService {
	int deleteByPrimaryKey(Long id);
    int insert(MessageHandling record);
    MessageHandling selectByPrimaryKey(Long id);
    List<MessageHandling> selectAll();
    int updateByPrimaryKey(MessageHandling record);
	PageResult queryByConditionPage(QueryObject qo);
}
