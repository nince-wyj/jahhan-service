package ${serviceInfo.codePara["impl_package_path"]};

import ${serviceInfo.codePara["abstrImpl_package_path"]}.Abstr${tableInfo.className}Impl;
import ${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className};

/*
 * 开发人员在此实现接口方法
 *
 * @author code-generate-service
 */
public class ${tableInfo.className}DaoImpl  extends Abstr${tableInfo.className}Impl {
	
	@Override
	protected boolean isCachable() {
		return false;//如果需要使用缓存，请使用${tableInfo.className}DaoCache
	}
}