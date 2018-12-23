package ${basePackage}.service.impl;

import java.util.List;

import ${basePackage}.dao.I${className}DAO;
import ${basePackage}.domain.${className};
import ${basePackage}.service.I${className}Service;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public class ${className}ServiceImpl implements I${className}Service {

	private I${className}DAO ${className?uncap_first}DAO;

	public void set${className}DAO(I${className}DAO ${className?uncap_first}DAO) {
		this.${className?uncap_first}DAO = ${className?uncap_first}DAO;
	}

	@Override
	public void save(${className} o) {
		this.${className?uncap_first}DAO.save(o);
	}

	@Override
	public void update(${className} o) {
		this.${className?uncap_first}DAO.update(o);
	}

	@Override
	public void delete(${className} o) {
		this.${className?uncap_first}DAO.delete(o);
	}

	@Override
	public ${className} get(Long id) {
		return this.${className?uncap_first}DAO.get(id);
	}

	@Override
	public List<${className}> list() {
		return this.${className?uncap_first}DAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.${className?uncap_first}DAO.query(qo);
	}

}
