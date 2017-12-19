package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * 缓存生成监听类:rep.ftl
 * 
 */
public class RepMaker extends BaseMaker {
	
	public RepMaker() {
		super();
		setPackageName("rep_package_path");
		setCodeNamePre("");
		setCodeNameSuf("Rep.java");
		setTemplateName("rep");
	}
}