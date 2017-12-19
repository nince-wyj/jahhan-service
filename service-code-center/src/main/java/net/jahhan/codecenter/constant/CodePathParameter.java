package net.jahhan.codecenter.constant;

import net.jahhan.common.extension.utils.PropertiesUtil;

public class CodePathParameter {
	public static final String pojo_package_path = PropertiesUtil.get("codecenter", "codePath.pojo");
	public static final String cache_package_path = PropertiesUtil.get("codecenter", "codePath.cache");
	public static final String dao_package_path = PropertiesUtil.get("codecenter", "codePath.dao");
	public static final String abstrImpl_package_path = PropertiesUtil.get("codecenter", "codePath.abstrImpl");
	public static final String abstrdao_package_path = PropertiesUtil.get("codecenter", "codePath.abstrdao");
	public static final String ibatis_package_path = PropertiesUtil.get("codecenter", "codePath.ibatis");
	public static final String impl_package_path = PropertiesUtil.get("codecenter", "codePath.impl");
	public static final String page_package_path = PropertiesUtil.get("codecenter", "codePath.page");
	public static final String rep_package_path = PropertiesUtil.get("codecenter", "codePath.rep");
}
