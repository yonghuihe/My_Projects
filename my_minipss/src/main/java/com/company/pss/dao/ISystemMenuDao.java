package com.company.pss.dao;

import java.util.List;

import com.company.pss.domain.Role;
import com.company.pss.domain.SystemMenu;

public interface ISystemMenuDao extends IGenericDao<SystemMenu> {

	List<SystemMenu> queryMenuChildren(String sn);

	List<SystemMenu> queryMenuChildren(List<Role> roles, String sn);


}
