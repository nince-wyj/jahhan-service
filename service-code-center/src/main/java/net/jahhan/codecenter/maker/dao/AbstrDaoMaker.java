package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * 接口类:abstrdao.ftl ps:开发人员不能修改
 * 
 */
public class AbstrDaoMaker extends BaseMaker {

	public AbstrDaoMaker() {
		super();
		setPackageName("abstrdao_package_path");
		setCodeNamePre("Abstr");
		setCodeNameSuf("Dao.java");
		setTemplateName("abstrDao");
	}
}