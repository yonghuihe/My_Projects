package com.company.ssh.service.impl;

import com.company.ssh.dao.IRoleDao;
import com.company.ssh.domain.Role;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IRoleService;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    private IRoleDao roleDao;

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void save(Role r) {
        roleDao.save(r);
    }

    public void update(Role r) {
        roleDao.update(r);
    }

    public void delete(Role r) {
        roleDao.delete(r);
    }

    public Role get(Long id) {
        return roleDao.get(id);
    }

    public List<Role> list() {
        return roleDao.list();
    }

    public PageResult query(QueryObject qo) {
        return roleDao.query(qo);
    }

}
