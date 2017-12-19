package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * cache类:cache.ftl ps:开发人员可能修改
 *
 */
public class CacheMaker extends BaseMaker {

	public CacheMaker() {
		super();
		setPackageName("cache_package_path");
		setCodeNamePre("");
		setCodeNameSuf("DaoCache.java");
		setTemplateName("cache");
	}
}
