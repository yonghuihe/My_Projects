package ${basePackage}.service.impl;

import java.util.List;

import ${basePackage}.dao.I${className}Dao;
import ${basePackage}.domain.${className};
import ${basePackage}.service.I${className}Service;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class ${className}ServiceImpl implements I${className}Service {

	private I${className}Dao ${className?uncap_first}Dao;

	public void set${className}Dao(I${className}Dao ${className?uncap_first}Dao) {
		this.${className?uncap_first}Dao = ${className?uncap_first}Dao;
	}

	@Override
	public void save(${className} o) {
		this.${className?uncap_first}Dao.save(o);
	}

	@Override
	public void update(${className} o) {
		this.${className?uncap_first}Dao.update(o);
	}

	@Override
	public void delete(${className} o) {
		this.${className?uncap_first}Dao.delete(o);
	}

	@Override
	public ${className} get(Long id) {
		return this.${className?uncap_first}Dao.get(id);
	}

	@Override
	public List<${className}> list() {
		return this.${className?uncap_first}Dao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.${className?uncap_first}Dao.query(qo);
	}

}
