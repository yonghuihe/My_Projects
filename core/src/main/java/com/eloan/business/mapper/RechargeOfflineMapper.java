package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.query.RechargeOfflineQueryObject;

public interface RechargeOfflineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);

    List<RechargeOffline> selectAll();

    int updateByPrimaryKey(RechargeOffline record);
    
    int queryForCount(RechargeOfflineQueryObject qo);
    
    List<RechargeOffline> query(RechargeOfflineQueryObject qo);
    
}