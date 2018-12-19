package ${cfg.beanMapperPackage};

import ${cfg.beanPoPackage}.${table.beanName}PO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ${table.tableComment}
 * @author BOY_Code_Generator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${table.beanName}Mapper {

    ${table.beanName}PO get(Integer id);

    int add(${table.beanName}PO t);

    int remove(@Param("id") Integer id, @Param("opUser") String opUser);

    int update(${table.beanName}PO t);

    int updateIgnoreNull(${table.beanName}PO t);

    List<${table.beanName}PO> list();
}