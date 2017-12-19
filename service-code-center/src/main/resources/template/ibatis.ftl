<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- 自动生成代码，请勿修改 -->
<mapper namespace="${tableInfo.className?upper_case}">
	<!-- 添加-->
	<insert id="add${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}" useGeneratedKeys="true" keyProperty="${tableInfo.getSingleKey().javaName}">
		INSERT INTO ${tableInfo.dbTableName} (	
		<trim prefixOverrides=",">
			<#list tableInfo.columnInfoList as col>
			<if test="${col.javaName} != null"> , ${col.dbColumnName} </if>
			</#list> 
		</trim>   
		)
		VALUES (               
		<trim prefixOverrides=",">
			<#list tableInfo.columnInfoList as col>
			<if test="${col.javaName} != null"> , ${"#{"+col.javaName+"}"} </if>
			</#list>  
		</trim>   
		)  
	</insert>
	
	<!-- 删除 -->
	<delete id="del${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
		DELETE FROM ${tableInfo.dbTableName}        
		WHERE ${tableInfo.getSingleKey().dbColumnName} = ${"#{"+tableInfo.getSingleKey().javaName+"}"}
	</delete>
	
	<!-- 删除指定的主键列表的记录-->
	<delete id="del${tableInfo.className}ByIds" parameterType="collection">
		DELETE FROM ${tableInfo.dbTableName}         
		WHERE ${tableInfo.getSingleKey().dbColumnName} IN 
		<foreach item="item" collection="list" open="(" separator="," close=")">  
			${"#{"+"item"+"}"}  
		</foreach>
	</delete>
	
	<!-- 全部更新 -->
	<update id="update${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
		UPDATE  ${tableInfo.dbTableName}       
		SET  
		<trim prefixOverrides=",">
		<#list tableInfo.getNonKeyColumns() as col>
		<#if tableInfo.tablePara["methodExtention"] == "true" >
			<#if col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_Increase != null"> 
				, ${col.dbColumnName} = ${col.dbColumnName} + ${"#{"+col.javaName+"_Increase}"} 
			</if>
			<if test="${col.javaName}_Increase == null"> 
				, ${col.dbColumnName} = ${"#{"+col.javaName+"}"} 
			</if>
			<#else>
				, ${col.dbColumnName} = ${"#{"+col.javaName+"}"}
			</#if>
		<#else>
				, ${col.dbColumnName} = ${"#{"+col.javaName+"}"}
		</#if>
		</#list>
		</trim>
		WHERE
			${tableInfo.getSingleKey().dbColumnName} = ${"#{"+tableInfo.getSingleKey().javaName+"}"}
	</update>
	
	<!-- 部分更新 -->
	<update id="updatePart${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
		UPDATE  ${tableInfo.dbTableName}       
		SET  
		<trim prefixOverrides=",">
			<#list tableInfo.getNonKeyColumns() as col>
			<#if tableInfo.tablePara["methodExtention"] == "true" >
				<#if col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_Increase != null"> 
				, ${col.dbColumnName} = ${col.dbColumnName} + ${"#{"+col.javaName+"_Increase}"}
			</if>
			<if test="${col.javaName}_Increase == null and ${col.javaName} != null"> 
				, ${col.dbColumnName}= ${"#{"+col.javaName+"}"}
			</if>
				<#else>
			<if test="${col.javaName} != null">, ${col.dbColumnName}= ${"#{"+col.javaName+"}"} </if>
				</#if>
			<#else>
			<if test="${col.javaName} != null">, ${col.dbColumnName}= ${"#{"+col.javaName+"}"} </if>
			</#if>
			</#list>
		</trim>
		WHERE 
			${tableInfo.getSingleKey().dbColumnName} = ${"#{"+tableInfo.getSingleKey().javaName+"}"}
	</update>
	
	<!-- 修改指定的主键列表的记录，部分更新-->
	<update id="updatePart${tableInfo.className}ByIds" parameterType="map">
		UPDATE  ${tableInfo.dbTableName}       
		SET
		<trim prefixOverrides=",">
			<#list tableInfo.getNonKeyColumns() as col>
			<#if tableInfo.tablePara["methodExtention"] == "true" >
				<#if col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="newObj.${col.javaName}_Increase != null"> 
				, ${col.dbColumnName}= ${col.dbColumnName} + ${"#{"+"newObj."+col.javaName+"_Increase}"}
			</if>
			<if test="newObj.${col.javaName}_Increase == null and newObj.${col.javaName} != null"> 
				, ${col.dbColumnName}= ${"#{"+"newObj."+col.javaName+"}"}
			</if>
				<#else>
			<if test="newObj.${col.javaName} != null">, ${col.dbColumnName}= ${"#{"+"newObj."+col.javaName+"}"} </if>
				</#if>
			<#else>
			<if test="newObj.${col.javaName} != null">, ${col.dbColumnName}= ${"#{"+"newObj."+col.javaName+"}"} </if>
			</#if>
			</#list>
		</trim>  
		WHERE ${tableInfo.getSingleKey().dbColumnName} IN
		<foreach item="item" collection="ids" open="(" separator="," close=")">  
			${"#{"+"item"+"}"}  
		</foreach>
		<if test="oldObj!= null">
			<#list tableInfo.getNonKeyColumns() as col>
			<#if tableInfo.tablePara["methodExtention"] == "true" >
				<#if col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="oldObj.${col.javaName}_RangeType != null"> 
				<if test="oldObj.${col.javaName}_Begin != null">
					<if test="oldObj.${col.javaName}_RangeType == 'open_open' or oldObj.${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+"oldObj."+col.javaName+"_Begin}"} ]]></if>
					<if test="oldObj.${col.javaName}_RangeType == 'close_open' or oldObj.${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+"oldObj."+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="oldObj.${col.javaName}_End != null">
					<if test="oldObj.${col.javaName}_RangeType == 'open_open' or oldObj.${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+"oldObj."+col.javaName+"_End}"} ]]></if>
					<if test="oldObj.${col.javaName}_RangeType == 'close_close' or oldObj.${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+"oldObj."+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="oldObj.${col.javaName}_RangeType == null and oldObj.${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+"oldObj."+col.javaName+"}"} </if>
				<#else>
			<if test="oldObj.${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+"oldObj."+col.javaName+"}"} </if>
				</#if>
			<if test="oldObj.${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="oldObj.${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
			<#else>
			<if test="oldObj.${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+"oldObj."+col.javaName+"}"} </if>
			</#if>
			</#list>
		</if>
	</update>
	
	<!-- 查询单条-->
	<select id="query${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
		SELECT	
		<#list tableInfo.columnInfoList as col>
			 ${col.dbColumnName} as  ${col.javaName}  <#if col_has_next>,</#if>
		</#list> 
		FROM  ${tableInfo.dbTableName} 
		<trim prefix="where" prefixOverrides="and">
		<#list tableInfo.columnInfoList as col>
		<#if tableInfo.tablePara["methodExtention"] == "true" >
			<#if col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
		<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
		</#if>
		</#list>
		</trim>
		LIMIT 1
	</select>
	
	<!-- 精确查询 -->
	<select id="list${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
		SELECT	
		<#list tableInfo.columnInfoList as col>
			 ${col.dbColumnName} as  ${col.javaName}  <#if col_has_next>,</#if>
		</#list>   
		FROM  ${tableInfo.dbTableName}  
		<trim prefix="where" prefixOverrides="and">
		<#list tableInfo.columnInfoList as col>
		<#if tableInfo.tablePara["methodExtention"] == "true" >
			<#if col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
		<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
		</#if>
		</#list>
		</trim>
		<if test="order_by != null"> ORDER BY ${r'${order_by}'} </if>
		LIMIT ${"#{"+"beginDATAIndex"+"}"},${"#{"+"pageSize"+"}"}
	</select>
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	<!-- 模糊查询 -->
	<select id="likeList${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
    	SELECT	
		<#list tableInfo.columnInfoList as col>
			 ${col.dbColumnName} as  ${col.javaName}  <#if col_has_next>,</#if>
		</#list>   
		FROM  ${tableInfo.dbTableName}  
		<trim prefix="where" prefixOverrides="and">
		<#list tableInfo.columnInfoList as col>
			<#if col.javaType??>
			<#if col.javaType == "String">
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} like "${"%${"+col.javaName+"}%"}" </if>
			<#elseif col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			</#if>
			</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
		</#list>
		</trim>
		<if test="order_by != null"> ORDER BY ${r'${order_by}'} </if>
		LIMIT ${"#{"+"beginDATAIndex"+"}"},${"#{"+"pageSize"+"}"}
	</select>
	</#if>
	
	<!-- 精确查询数量 -->
	<select id="count${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="_long">
		SELECT COUNT(1)	                          			   	     		        
		FROM ${tableInfo.dbTableName}    
		<trim prefix="where" prefixOverrides="and">
			<#list tableInfo.columnInfoList as col>
			<#if tableInfo.tablePara["methodExtention"] == "true" >
				<#if col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
				<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
				</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
			<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			</#if>
			</#list>
		</trim>
	</select>
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	<!-- 模糊查询数量 -->
	<select id="countLike${tableInfo.className}" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="_long">
		SELECT COUNT(1)	                          			   	     		        
		FROM ${tableInfo.dbTableName}    
		<trim prefix="where" prefixOverrides="and">
			<#list tableInfo.columnInfoList as col>
				<#if col.javaType??>
					<#if col.javaType == "String">
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} like "${"%${"+col.javaName+"}%"}" </if>
					<#elseif col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
					<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
					</#if>
				</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
			</#list>
		</trim>
	</select>
	</#if>
	
	<!-- 根据id查询-->
	<select id="list${tableInfo.className}ByIds" parameterType="collection" resultType="${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className}">
		SELECT	
			<#list tableInfo.columnInfoList as col>
			${col.dbColumnName} as  ${col.javaName}  <#if col_has_next>,</#if>
			</#list>     
		FROM ${tableInfo.dbTableName}    
		WHERE ${tableInfo.getSingleKey().dbColumnName} IN 
		<foreach item="item" collection="list" open="(" separator="," close=")">  
			${"#{"+"item"+"}"}  
		</foreach>
	</select>
	
	<!-- 根据精确条件获取所有主键列表-->
	<select id="listIds" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="${tableInfo.getSingleKey().javaType}">
		SELECT	
			${tableInfo.getSingleKey().dbColumnName} as ${tableInfo.getSingleKey().javaName}
		FROM ${tableInfo.dbTableName}  
		<trim prefix="where" prefixOverrides="and">
			<#list tableInfo.columnInfoList as col>
			<#if tableInfo.tablePara["methodExtention"] == "true" >
				<#if col.javaType??>
				<#if col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
				<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
				</#if>
				</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
			<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
			</#if>
			</#list>
		</trim>
		LIMIT ${"#{"+"beginDATAIndex"+"}"},${"#{"+"pageSize"+"}"}
	</select>
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	<!-- 根据模糊条件获取所有主键列表-->
	<select id="likeListIds" parameterType="${serviceInfo.codePara["pojo_package_path"]}.page.${tableInfo.className}Page" resultType="${tableInfo.getSingleKey().javaType}">
		SELECT	
			${tableInfo.getSingleKey().dbColumnName} as ${tableInfo.getSingleKey().javaName}
		FROM ${tableInfo.dbTableName}  
		<trim prefix="where" prefixOverrides="and">
			<#list tableInfo.columnInfoList as col>
				<#if col.javaType??>
				<#if col.javaType == "String">
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} like "${"%${"+col.javaName+"}%"}" </if>
				<#elseif col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
			<if test="${col.javaName}_RangeType != null"> 
				<if test="${col.javaName}_Begin != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} > ${"#{"+col.javaName+"_Begin}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_open' or ${col.javaName}_RangeType == 'close_close'"><![CDATA[ AND ${col.dbColumnName} >= ${"#{"+col.javaName+"_Begin}"} ]]></if>
				</if>
				<if test="${col.javaName}_End != null">
					<if test="${col.javaName}_RangeType == 'open_open' or ${col.javaName}_RangeType == 'close_open'"><![CDATA[ AND ${col.dbColumnName} < ${"#{"+col.javaName+"_End}"} ]]></if>
					<if test="${col.javaName}_RangeType == 'close_close' or ${col.javaName}_RangeType == 'open_close'"><![CDATA[ AND ${col.dbColumnName} <= ${"#{"+col.javaName+"_End}"} ]]></if>
				</if>
			</if>
			<if test="${col.javaName}_RangeType == null and ${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
				<#else>
			<if test="${col.javaName} != null"> AND ${col.dbColumnName} = ${"#{"+col.javaName+"}"} </if>
				</#if>
				</#if>
			<if test="${col.javaName}_List != null">
				AND ${col.dbColumnName} IN
				<foreach item="item" collection="${col.javaName}_List" open="(" separator="," close=")">  
					${"#{"+"item"+"}"}  
				</foreach>
			</if>
			</#list>
		</trim>
		LIMIT ${"#{"+"beginDATAIndex"+"}"},${"#{"+"pageSize"+"}"}
	</select>
	</#if>
	
	<!-- 获取最大主键-->
	<select id="selectMax${tableInfo.className}Sequence" resultType="${tableInfo.getSingleKey().getSamllJavaType()}">
		SELECT max(${tableInfo.getSingleKey().dbColumnName}) as max_sequence FROM  ${tableInfo.dbTableName}    
	</select>	
</mapper>