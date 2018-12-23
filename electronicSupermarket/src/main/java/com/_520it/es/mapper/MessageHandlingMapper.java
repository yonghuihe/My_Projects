package com._520it.es.mapper;

import com._520it.es.domain.MessageHandling;
import com._520it.es.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageHandlingMapper {
    int deleteByPrimaryKey(Long id);
    int insert(MessageHandling record);
    MessageHandling selectByPrimaryKey(Long id);
    List<MessageHandling> selectAll();
    int updateByPrimaryKey(MessageHandling record);
	Long queryByCondictionCount(QueryObject qo);
	List<MessageHandling> queryByConditionResult(QueryObject qo);
}