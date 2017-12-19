package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * 接口实现类:impl.ftl ps:开发人员可以修改
 * 
 */
public class ImplMaker extends BaseMaker {

	public ImplMaker() {
		super();
		setPackageName("impl_package_path");
		setCodeNamePre("");
		setCodeNameSuf("DaoImpl.java");
		setTemplateName("impl");
	}
}