package com.eloan.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.query.PageResult;
import com.eloan.base.query.QueryObject;
import com.eloan.business.domain.CompanyBankInfo;
import com.eloan.business.mapper.CompanyBankInfoMapper;
import com.eloan.business.service.ICompanyBankInfoService;

@Service
public class CompanyBankInfoServiceImpl implements ICompanyBankInfoService {

	@Autowired
	private CompanyBankInfoMapper companyBankInfoMapper; 
	
	@Override
	public void save(CompanyBankInfo info) {
		if(info.getId()!=null){
			this.companyBankInfoMapper.updateByPrimaryKey(info);
		}else{
			this.companyBankInfoMapper.insert(info);
		}
	}

	@Override
	public PageResult query(QueryObject qo) {
		int count = this.companyBankInfoMapper.queryForCount(qo);
		if(count>0){
			List<CompanyBankInfo> list = this.companyBankInfoMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public List<CompanyBankInfo> list() {
		return this.companyBankInfoMapper.selectAll();
	}

}
