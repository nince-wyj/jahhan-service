package ${serviceInfo.codePara["abstrdao_package_path"]};

import java.sql.SQLException;
import java.util.List;

import ${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className};
import ${serviceInfo.codePara["page_package_path"]}.${tableInfo.className}Page;
import net.jahhan.jdbc.pojo.page.PagedResult;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
public interface Abstr${tableInfo.className}Dao
{
   /**
	* 增加 
	*/
	int add${tableInfo.className}(${tableInfo.className} ${tableInfo.className?uncap_first});
	
	/**
	* 批量增加 
	*/
	int addBatch${tableInfo.className}(List<${tableInfo.className}> ${tableInfo.className?uncap_first}List);
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	int resetAll${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName},${tableInfo.className} ${tableInfo.className?uncap_first});
	
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/	
	int updatePart${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName},${tableInfo.className} ${tableInfo.className?uncap_first});	
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<${tableInfo.getSingleKey().javaType}>  ${tableInfo.getSingleKey().javaName}s 主键列表
	 * @param ${tableInfo.className} condition${tableInfo.className} 旧值
	 * @param ${tableInfo.className} new${tableInfo.className} 新值
	 */
	int updatePartByIds(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s,${tableInfo.className}Page condition${tableInfo.className},${tableInfo.className} new${tableInfo.className});
	
   /**
	* 删除 
	*/
	int del${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName});
	
	/** 
	 * 加载
	 * @throws SQLException
	 */
	${tableInfo.className} query${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName});
	
	/**
	 * 根据主键Id列出对象，不保证记录的顺序
	 * @param ids 以逗号分隔的id列表
	 * @return
	 */
	List<${tableInfo.className}> list${tableInfo.className}ByIds(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s);
	
	/**
	 * 加载指定条件的所有id
	 */
	List<${tableInfo.getSingleKey().javaType}> listIds(${tableInfo.className}Page ${tableInfo.className?uncap_first});
	
	/** 分页查询 **/
	public PagedResult<${tableInfo.className}> pagedResultOfList${tableInfo.className}(${tableInfo.className}Page ${tableInfo.className?uncap_first});
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	/** 分页模糊查询 **/
	public PagedResult<${tableInfo.className}> pagedResultOfLike${tableInfo.className}(${tableInfo.className}Page ${tableInfo.className?uncap_first});
	</#if>	
	
	/**
	 * 批量删除
	 */	
	int delByIds(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s);
	
	/**
	 * 获取最大主键
	 */		
	${tableInfo.getSingleKey().getSamllJavaType()} getMaxSequence();
}
