package net.jahhan.codecenter.constant;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class FreeMarkConfig {
	private static Configuration cfg = new Configuration();

	static {
		URL url = FreeMarkConfig.class.getClassLoader().getResource("/codecenter.properties");
		String resourcePath = null;
		if (null != url) {
			resourcePath = url.getPath();
		} else {
			try {
				resourcePath = Thread.currentThread().getContextClassLoader().getResource("codecenter.properties")
						.toURI().getPath();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		try {
			cfg.setDirectoryForTemplateLoading(new File(resourcePath).getParentFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
	}

	public static Configuration getConfiguration() {
		return cfg;
	}
}
