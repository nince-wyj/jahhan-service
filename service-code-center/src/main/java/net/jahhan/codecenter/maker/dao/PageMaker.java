package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * page实现类:page.ftl
 * 
 */
public class PageMaker extends BaseMaker {

	public PageMaker() {
		super();
		setPackageName("page_package_path");
		setCodeNamePre("");
		setCodeNameSuf("Page.java");
		setTemplateName("page");
	}
}