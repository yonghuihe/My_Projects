package ${basePackage}.service;

import java.util.List;

import ${basePackage}.domain.${className};
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface I${className}Service {
	void save(${className} o);

	void update(${className} o);

	void delete(${className} o);

	${className} get(Long id);

	List<${className}> list();

	PageResult query(QueryObject qo);
}
