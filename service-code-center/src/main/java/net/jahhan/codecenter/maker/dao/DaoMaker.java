package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * 接口类:dao.ftl ps:开发人员可能修改
 * 
 */
public class DaoMaker extends BaseMaker {
	
	public DaoMaker() {
		super();
		setPackageName("dao_package_path");
		setCodeNamePre("");
		setCodeNameSuf("Dao.java");
		setTemplateName("dao");
	}
}