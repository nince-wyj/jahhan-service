package ${serviceInfo.codePara["pojo_package_path"]};

import java.sql.Timestamp;
import java.sql.Date;
import java.math.BigDecimal;

import javax.validation.constraints.*;

import net.jahhan.jdbc.SuperPojo;
import net.jahhan.jdbc.validategroup.*;
import org.hibernate.validator.constraints.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${tableInfo.dbTableName}:${tableInfo.comment}
 * 自动生成,开发人员请勿修改.
 * @author code-generate-service
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "${tableInfo.comment}")
<#if tableInfo.tableNote??>
<#list tableInfo.tableNote as tNote>
${tNote}
</#list>
</#if>
public class ${tableInfo.className} extends SuperPojo<${tableInfo.className}> {

	private static final long serialVersionUID = 10000000L;
		
<#list tableInfo.columnInfoList as col>
	<#if !col.canNull>
	<#if col.primaryKey>
	@NotNull(groups = { Create.class, Modify.class, Reset.class }, message = "${col.javaName}不能为空")
	<#elseif !col.primaryKey>
	@NotNull(groups = { Create.class, Reset.class }, message = "${col.javaName}不能为空")
	</#if>	
	</#if>	
	@ApiModelProperty(value = "${col.comment}")
	<#if col.columnNote??>
	<#list col.columnNote as cNote>
	${cNote}
	</#list>
	</#if>
	<#if col.javaType == "String">
	@Length(min = 0, max = ${col.dbLength?c}, groups = { Create.class, Modify.class, Reset.class }, message = "${col.javaName}字符串长度需小于${col.dbLength}")
	</#if>
	private ${col.javaType} ${col.javaName};
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	<#if col.javaType??>
	<#if col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
	/**
	 * ${col.dbColumnName}数值增加
	 */
	private ${col.javaType} ${col.javaName}_Increase;  
	</#if>
	</#if>
	</#if>  

</#list>
}