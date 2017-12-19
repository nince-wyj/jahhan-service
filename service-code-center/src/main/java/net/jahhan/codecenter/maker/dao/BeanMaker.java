package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * pojo类:bean.ftl  ps:开发人员不能修改
 * 
 */
public class BeanMaker extends BaseMaker {
	public static final String POJO_PACKAGE_PATH = ".pojo_package_path";

	public BeanMaker() {
		super();
		setPackageName("pojo_package_path");
		setCodeNamePre("");
		setCodeNameSuf(".java");
		setTemplateName("bean");
	}
}
