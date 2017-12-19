package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;

/**
 * 接口抽象实现类:abstr.ftl ps:开发人员不能修改的类
 * 
 */
public class AbstrImplMaker extends BaseMaker {

	public AbstrImplMaker() {
		super();
		setPackageName("abstrImpl_package_path");
		setCodeNamePre("Abstr");
		setCodeNameSuf("Impl.java");
		setTemplateName("abstrImpl");
	}
}