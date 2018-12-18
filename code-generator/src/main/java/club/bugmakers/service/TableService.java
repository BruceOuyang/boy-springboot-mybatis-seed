package club.bugmakers.service;

import club.bugmakers.bean.Column;
import club.bugmakers.bean.GenConf;
import club.bugmakers.bean.Table;
import club.bugmakers.dao.TableDao;
import club.bugmakers.util.ListUtil;
import club.bugmakers.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 数据库表数据查询服务
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Service
public class TableService {

	private static final Logger logger = LoggerFactory.getLogger(TableService.class);

	@Resource
	private TableDao tableDao;
	@Resource
	private GenConf genConf;

	private static final String BASE_PO = "BasePO";

	public List<Table> getTableInfoList() {

		// 获取所有表
		List<Table> dataList = tableDao.getTableList(genConf.getSchema(),genConf.getTableInclude());

		for (Table data : dataList) {

			// 获取表的所有列
			data.setColumnList(tableDao.getColumnList(genConf.getSchema(), data.getTableName()));

			// 获取表的主键
			data.setPkColumnList(tableDao.getPKList(genConf.getSchema(), data.getTableName()));
			if (ListUtil.isEmpty(data.getPkColumnList())) {
				logger.error("table has no PK, table = {}", data.getTableName());
			}

			// 设置主键字段名称
			List<String> pkPropertyList = Lists.newArrayList();
			for (String pk : data.getPkColumnList()) {
				pkPropertyList.add(StrUtil.toCamelStyle(pk));
			}
			data.setPkPropertyList(pkPropertyList);

			// 设置其他字段名称和类型
			Set<String> propertySet = Sets.newHashSet();
			Set<String> propertyTypeSet = Sets.newHashSet();
			for (Column column : data.getColumnList()) {
				propertySet.add(column.getPropertyName());
				propertyTypeSet.add(column.getPropertyType());
			}

			// 设置公共基类
			data.setBaseBeanName(BASE_PO);

			// 需要导入的类
			if (propertyTypeSet.contains("BigDecimal")) {
				data.getImportBeanList().add("java.math.BigDecimal");
			}
			if (propertyTypeSet.contains("Date")) {
				data.getImportBeanList().add("java.util.Date");
			}

			// 设置属性字段
			List<Column> propertyList = Lists.newArrayList();
			if (BASE_PO.equals(data.getBaseBeanName())) {
				for (Column column : data.getColumnList()) {
					String propertyName = column.getPropertyName();
					if ("id".equals(propertyName) || "version".equals(propertyName) || "isDelete".equals(propertyName) ||
							"createDt".equals(propertyName) || "createBy".equals(propertyName) ||
							"updateDt".equals(propertyName) || "updateBy".equals(propertyName)) {
						continue;
					}
					propertyList.add(column);
				}
			} else {
				propertyList = data.getColumnList();
			}

			data.setPropertyList(propertyList);

		}
		return dataList;
	}

}
