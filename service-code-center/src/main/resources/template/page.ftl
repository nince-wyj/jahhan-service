package ${serviceInfo.codePara["page_package_path"]};

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import net.jahhan.jdbc.pojo.page.Pagable;
import net.jahhan.jdbc.pojo.page.Paged;
import ${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className};

/**
 * ${tableInfo.dbTableName}:${tableInfo.comment}
 * 开发人员在此可新增参数
 * @author code-generate-service
 */
public class ${tableInfo.className}Page extends ${tableInfo.className} implements Pagable{
    private static final long serialVersionUID = -100000L;
    
    private Paged paged=new Paged();
    private String order_by;
    private Boolean nextPage;
    private String group_by;
    
    public String getGroup_by() {
		return group_by;
	}

	public void setGroup_by(String group_by) {
		this.group_by = group_by;
	}
	
	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}
	@Override
	public Integer getBeginDATAIndex() {
		return paged.getBeginDATAIndex();
	}
	
	@Override
	public void setPageSize(int size) {
		paged.setPageSize(size);
	}
	@Override
	public void setPageIndex(int index) {
		paged.setPageIndex(index);
	}
	
	@Override
	public int getPageSize() {
		return paged.getPageSize();
	}
	
	@Override
	public void setNextPage(boolean nextPage) {
	    this.nextPage = nextPage;
	}
    
	@Override
	public boolean isNextPage() {
	    return this.nextPage;
	}    
<#if tableInfo.tablePara["methodExtention"] == "true" >
<#list tableInfo.columnInfoList as col>
	<#if col.javaType??>
	/**
	 * ${col.dbColumnName}列表筛选
	 */
	private List<${col.javaType}> ${col.javaName}_List;  
	public  List<${col.javaType}> get${col.javaName?cap_first}_List() { return ${col.javaName}_List;}
	public void  set${col.javaName?cap_first}_List(List<${col.javaType}> ${col.javaName}_List) { this.${col.javaName}_List=${col.javaName}_List;}
	
	<#if col.javaType == "Timestamp" || col.javaType == "Date" || col.javaType == "Long" || col.javaType == "Integer" || col.javaType == "BigDecimal">
	/**
	 * ${col.dbColumnName}时间或数值范围选择
	 */
	private ${col.javaType} ${col.javaName}_Begin;  
	public  ${col.javaType} get${col.javaName?cap_first}_Begin() { return ${col.javaName}_Begin;}
	public void  set${col.javaName?cap_first}_Begin(${col.javaType} ${col.javaName}_Begin) { this.${col.javaName}_Begin=${col.javaName}_Begin;}
	
	private ${col.javaType} ${col.javaName}_End;  
	public  ${col.javaType} get${col.javaName?cap_first}_End() { return ${col.javaName}_End;}
	public void  set${col.javaName?cap_first}_End(${col.javaType} ${col.javaName}_End) { this.${col.javaName}_End=${col.javaName}_End;}
	
	private String ${col.javaName}_RangeType;  
	public  String get${col.javaName?cap_first}_RangeType() { return ${col.javaName}_RangeType;}
	public void  set${col.javaName?cap_first}_RangeType(RangeEnum ${col.javaName}_RangeType) { this.${col.javaName}_RangeType=${col.javaName}_RangeType.typeString();}
	
	</#if>
	</#if>
</#list>
</#if>	
}
