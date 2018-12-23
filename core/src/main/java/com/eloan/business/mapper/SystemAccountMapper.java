package com.eloan.business.mapper;

import com.eloan.business.domain.SystemAccount;
import java.util.List;

public interface SystemAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAccount record);

    SystemAccount selectCurrent();

    List<SystemAccount> selectAll();

    int updateByPrimaryKey(SystemAccount record);
}