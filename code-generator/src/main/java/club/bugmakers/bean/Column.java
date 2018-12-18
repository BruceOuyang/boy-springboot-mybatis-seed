package club.bugmakers.bean;

import club.bugmakers.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库列实例
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
public class Column extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(Column.class);

    private String columnName;
    private String dataType;
    private String columnComment;

    private String propertyName;
    private String propertyType;

    private String getImportBean;
    private String extra;
    private String columnKey;

    public String getPropertyName() {
        if (columnName.contains("_")) {
            propertyName = StrUtil.toCamelStyle(columnName);
        } else {
            propertyName = columnName;
        }
        return propertyName;
    }

    public String getPropertyType() {

        if ("varchar".equals(dataType) || "char".equals(dataType)) {
            propertyType = "String";
        }
        if ("text".equals(dataType) || "tinytext".equals(dataType) || "mediumtext".equals(dataType) || "longtext".equals(dataType)) {
            propertyType = "String";
        }
        if ("blob".equals(dataType) || "mediumblob".equals(dataType) || "tinyblob".equals(dataType)) {
            propertyType = "String";
        }
        if ("bigint".equals(dataType)) {
            propertyType = "Long";
        }
        if ("float".equals(dataType)) {
            propertyType = "Float";
        }
        if ("double".equals(dataType)) {
            propertyType = "Double";
        }
        if ("smallint".equals(dataType) || "mediumint".equals(dataType) || "tinyint".equals(dataType) || "int".equals(dataType)) {
            propertyType = "Integer";
        }
        if ("set".equals(dataType)) {
            propertyType = "Byte";
        }
        if ("decimal".equals(dataType)) {
            propertyType = "BigDecimal";
        }
        if ("timestamp".equals(dataType) || "date".equals(dataType) || "datetime".endsWith(dataType)) {
            propertyType = "Date";
        }

        if (StrUtil.isBlank(propertyType)) {
            logger.error("Type Error=========> {}", dataType);
        }

        return propertyType;
    }

    /**
     * 是否自增主键
     * @return
     */
    public boolean isAutoIncPk() {
        return "auto_increment".equalsIgnoreCase(this.extra);
    }

    /**
     * 是否主键
     * @return
     */
    public boolean isPk() {
        return "PRI".equalsIgnoreCase(this.columnKey);
    }

    /**
     * 属性类型对应全类名
     * @return
     */
    public String getGetImportBean() {
        switch (getPropertyType()) {
            case "BigDecimal":
                this.getImportBean = "java.math.BigDecimal";
                break;
            case "Date":
                this.getImportBean = "java.util.Date";
                break;
            default:
                this.getImportBean = null;
        }
        return getImportBean;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public void setGetImportBean(String getImportBean) {
        this.getImportBean = getImportBean;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

}
