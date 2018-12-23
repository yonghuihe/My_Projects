package ${packageName}.mapper;

import ${packageName}.domain.${className};
import ${packageName}.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ${className}Mapper {
    int deleteByPrimaryKey(Long id);
    int insert(${className} record);
    ${className} selectByPrimaryKey(Long id);
    List<${className}> selectAll();
    int updateByPrimaryKey(${className} record);
	Long queryByCondictionCount(QueryObject qo);
	List<${className}> queryByConditionResult(QueryObject qo);
}