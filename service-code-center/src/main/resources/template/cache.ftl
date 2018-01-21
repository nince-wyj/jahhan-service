package ${serviceInfo.codePara["cache_package_path"]};

import ${serviceInfo.codePara["impl_package_path"]}.${tableInfo.className}DaoImpl;

/**
 * 自动生成,开发人员请勿修改.
 *
 * @author code-generate-service
 */
public class ${tableInfo.className}DaoCache extends ${tableInfo.className}DaoImpl{

	@Override
	protected boolean isCachable() {
	<#if tableInfo.tablePara["dataCache"] == "true" >
		return true;
	<#else>
		return false;
	</#if>
	}

}
