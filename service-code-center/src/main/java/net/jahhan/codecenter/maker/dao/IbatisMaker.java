package net.jahhan.codecenter.maker.dao;

import net.jahhan.codecenter.maker.BaseMaker;
import net.jahhan.common.extension.utils.PropertiesUtil;

/**
 * xml生成类:ibatis.xml ps:开发人员不能修改
 * 
 */
public class IbatisMaker extends BaseMaker {

	public IbatisMaker() {
		super();
		setPackageName("ibatis_package_path");
		setCodeNamePre("");
		setCodeNameSuf(".xml");
		setTemplateName("ibatis");
		setFilePath(PropertiesUtil.get("codecenter", "xml.path"));
	}
}