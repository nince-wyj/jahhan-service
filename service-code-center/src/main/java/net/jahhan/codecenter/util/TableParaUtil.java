package net.jahhan.codecenter.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.jahhan.codecenter.bean.ParaEntity;
import net.jahhan.codecenter.constant.CodePathParameter;
import net.jahhan.codecenter.constant.TableDefaultParameter;

public class TableParaUtil {

	public static Map<String, String> getTablePara(Map<String, String> tableParaMap) {
		Map<String, String> tablePara = new HashMap<>();
		Field[] fields = TableDefaultParameter.class.getFields();
		for (Field field : fields) {
			if (field.getType().equals(ParaEntity.class)) {
				try {
					ParaEntity paraEntity = (ParaEntity) field.get(null);
					tablePara.put(paraEntity.getKey(), paraEntity.getValue());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		Set<String> keySet = tableParaMap.keySet();
		for (String key : keySet) {
			if (null != tableParaMap.get(key)) {
				tablePara.put(key, tableParaMap.get(key));
			}
		}
		return tablePara;
	}

	public static Map<String, String> getCodePara(String servicePath) {
		Map<String, String> codePara = new HashMap<>();
		Field[] fields = CodePathParameter.class.getFields();
		for (Field field : fields) {
			if (field.getType().equals(String.class)) {
				try {
					String value = (String) field.get(null);
					if (value.startsWith("#")) {
						value = value.replace("#", servicePath);
					}
					codePara.put(field.getName(), value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return codePara;
	}
}
