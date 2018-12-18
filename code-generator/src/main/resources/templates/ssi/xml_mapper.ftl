<#assign crud_columns = [] />
<#assign columns = table.columnList />
<#list columns as col>
    <#if !(col.columnName =='id' || col.columnName == 'create_dt' || col.columnName == 'update_dt' || col.columnName == 'version' || col.columnName == 'is_delete')>
    <#assign crud_columns = crud_columns + [col] />
    </#if>
</#list>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${cfg.beanMapperPackage}.${table.beanName}Mapper">
	<!--查询字段-->
	<sql id="columns">
	<#list columns as col>
	    a.${col.columnName}<#if col_has_next>,</#if>
	</#list>
	</sql>
	
	<!--查询结果集-->
	<resultMap id="beanMap" type="${cfg.beanPoPackage}.${table.beanName}PO">
	<#list columns as col>
	    <result property="${col.propertyName}" column="${col.columnName}"/>
	</#list>
	</resultMap>

    <!--根据主键获取实体-->
    <select id="get" resultMap="beanMap">
        SELECT
        <include refid="columns"/>
        FROM
	        ${table.tableName} a
        WHERE
          <#list table.pkColumnList as pk>
            <#if pk_index gt 0> AND</#if>${pk} = <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse>
          </#list>
    </select>

    <!-- 新增记录 -->
    <insert id="add" useGeneratedKeys="true" keyProperty="id" parameterType="${cfg.beanPoPackage}.${table.beanName}PO">
        INSERT INTO ${table.tableName}
        (
		<#list crud_columns as col>
	        `${col.columnName}`<#sep>,
		</#list>

        )
        VALUES
        (
		<#list crud_columns as col>
			<#noparse>#{</#noparse>${col.propertyName}<#noparse>}</#noparse><#sep>,
		</#list>

        )
    </insert>

    <!--更新实体信息-->
    <update id="update" parameterType="${cfg.beanPoPackage}.${table.beanName}PO">
        UPDATE
	        ${table.tableName}
        SET
          <#list crud_columns as col>
           `${col.columnName}` = <#noparse>#{</#noparse>${col.propertyName}<#noparse>}</#noparse><#sep>,
          </#list>

        WHERE
          <#list table.pkColumnList as pk>
            <#if pk_index gt 0> AND</#if>${pk} = <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse>
          </#list>
    </update>

    <!--更新实体信息，null值字段不更新-->
    <update id="updateIgnoreNull" parameterType="${cfg.beanPoPackage}.${table.beanName}PO">
        UPDATE ${table.tableName}
        <set>
          <#list crud_columns as col>
          <if test="${col.propertyName} != null">
              `${col.columnName}` = <#noparse>#{</#noparse>${col.propertyName}<#noparse>}</#noparse>,
          </if>
		  </#list>
        </set>
        WHERE
          <#list table.pkColumnList as pk>
            <#if pk_index gt 0> AND</#if>${pk} = <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse>
          </#list>
    </update>

    <!--根据主键删除实体-->
    <delete id="remove">
        UPDATE ${table.tableName} SET is_delete = 1, update_by = <#noparse>#{</#noparse>opUser<#noparse>}</#noparse> WHERE <#list table.pkColumnList as pk><#if pk_index gt 0> AND</#if> ${pk} = <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse></#list>
    </delete>
	
	<!-- 列表查询 -->
	<select id="list" resultMap="beanMap">
	  SELECT
		<include refid="columns"/>
	  FROM
		${table.tableName} a
	  ORDER BY id DESC
	</select>
</mapper>