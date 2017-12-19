package net.jahhan.codecenter.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.jahhan.codecenter.bean.ServiceInfo;
import net.jahhan.codecenter.bean.TableInfo;
import net.jahhan.codecenter.constant.FreeMarkConfig;
import net.jahhan.codecenter.util.FileUtil;
import net.jahhan.common.extension.utils.Assert;
import net.jahhan.common.extension.utils.PropertiesUtil;

/**
 * 代码生成基础抽象类
 * 
 */
@Slf4j
public abstract class BaseMaker {
	private Configuration cfg;
	private Map<String, Object> ftlValueMap = new HashMap<String, Object>();
	@Getter
	@Setter
	private String packageName;
	@Getter
	@Setter
	private String templateName;
	@Getter
	@Setter
	private String codeNamePre;
	@Getter
	@Setter
	private String codeNameSuf;
	@Getter
	@Setter
	private String filePath = PropertiesUtil.get("codecenter", "src.path");

	public BaseMaker() {
		this.cfg = FreeMarkConfig.getConfiguration();
	}

	public void outPut(String ftlTemplate, String destJave, Map<String, Object> ftlValueMap) {
		Writer out = null;
		try {
			Template template = cfg.getTemplate(ftlTemplate);
			File file = new File(destJave);
			FileUtil.checkNewFile(file);
			// OutputStreamWriter的优点是能进行编码.FileOutputStream不能,他是二进制操作
			out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			template.process(ftlValueMap, out);
			out.flush();
			log.info("文件输出：{}",destJave);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void exec(TableInfo tableInfo,ServiceInfo serviceInfo) {
		try {
			this.output(tableInfo,serviceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getFtlValueMap() {
		return ftlValueMap;
	}

	public void putFtlValueMap(String key, Object value) {
		this.ftlValueMap.put(key, value);
	}

	public static void clean(ServiceInfo serviceInfo) {
		FileUtil.deleteAllFilesOfDir(getMakePath(serviceInfo));
	}

	public void output(TableInfo tableInfo, ServiceInfo serviceInfo) throws Exception {
		String packagePath = serviceInfo.getCodePara().get(getPackageName());
		Assert.notNullString(packagePath, 999);
		String dest = getMakePath(serviceInfo) + File.separator + getFilePath() + File.separator
				+ packagePath.replace(".", File.separator) + File.separator + getCodeNamePre()
				+ tableInfo.getClassName() + getCodeNameSuf();
		putFtlValueMap("tableInfo", tableInfo);
		putFtlValueMap("serviceInfo", serviceInfo);
		this.outPut("template/" + getTemplateName() + ".ftl", dest, getFtlValueMap());
	}

	public static String getMakePath(ServiceInfo serviceInfo) {
		return (PropertiesUtil.get("codecenter", "baseDir") + File.separator + serviceInfo.getServiceName())
				.replace(".", File.separator);
	};
}
