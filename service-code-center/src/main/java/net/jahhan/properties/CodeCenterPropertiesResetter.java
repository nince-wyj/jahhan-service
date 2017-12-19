package net.jahhan.properties;

import java.io.File;
import java.util.Properties;

import net.jahhan.api.PropertiesResetter;
import net.jahhan.common.extension.annotation.Order;
import net.jahhan.common.extension.annotation.PropertiesFile;

@PropertiesFile("codecenter")
@Order(1)
public class CodeCenterPropertiesResetter extends PropertiesResetter {

	@Override
	public void reset(Properties properties) {
		properties.setProperty("codePath.pojo", "#.pojo");
		properties.setProperty("codePath.page", "#.pojo.page");
		properties.setProperty("codePath.dao", "#.dao");
		properties.setProperty("codePath.rep", "#.dao.listen");
		properties.setProperty("codePath.abstrdao", "#.dao.abstrdao");
		properties.setProperty("codePath.abstrImpl", "#.dao.abstrimpl");
		properties.setProperty("codePath.impl", "#.dao.impl");
		properties.setProperty("codePath.cache", "#.dao.cache");
		properties.setProperty("codePath.ibatis", "mapper");

		properties.setProperty("src.path",
				"src" + File.separator + "main" + File.separator + "java" + File.separator);
		properties.setProperty("xml.path",
				"src" + File.separator + "main" + File.separator + "resources" + File.separator);
	}
}
