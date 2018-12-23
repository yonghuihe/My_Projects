package com.company.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.crm.domain.BatchInsertIds;
import com.company.crm.domain.Role;
import com.company.crm.query.QueryObject;

public interface RoleMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	Role selectByPrimaryKey(Long id);

	List<Role> selectAll();

	int updateByPrimaryKey(Role record);

	void insertRelation(@Param("rId") Long rId, @Param("pId") Long pId);

	void batchInsertRelation(List<BatchInsertIds> ids);

	void deleteRelation(Long id);

	Long queryPageResultCount(QueryObject qo);

	List<Role> queryPageResult(QueryObject qo);
}