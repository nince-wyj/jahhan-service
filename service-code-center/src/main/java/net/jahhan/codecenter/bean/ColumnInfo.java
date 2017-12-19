package net.jahhan.codecenter.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据库表字段信息
 */
@Data
public class ColumnInfo implements Serializable {
	private static final long serialVersionUID = 5848391048518681467L;

	@ApiModelProperty(value = "表字段名称")
	private String dbColumnName;

	@ApiModelProperty(value = "表字段类型")
	private String dbColumnType;

	@ApiModelProperty(value = "对应的java属性名称")
	private String javaName;

	@ApiModelProperty(value = "对应的java类型")
	private String javaType;

	@ApiModelProperty(value = "对应的java类型:Integer->int,Long->long")
	private String samllJavaType;

	@ApiModelProperty(value = "数据库长度")
	private int dbLength;

	@ApiModelProperty(value = "是否主键")
	private boolean primaryKey = false;

	@ApiModelProperty(value = "是否为空，true表示允许为空")
	private boolean canNull;

	@ApiModelProperty(value = "注释")
	private String comment;

	@ApiModelProperty(value = "是否自动增长")
	private String isAutoincrement;
	
	@ApiModelProperty(value = "字段注解集")
	private List<String> columnNote = null;
	
	@ApiModelProperty(value = "表字段属性")
	private Map<String, String> columnPara = new ConcurrentHashMap<>();
	
	public void setJavaType(String javaType) {
		this.javaType = javaType;
		setSamllJavaType(javaType);
	}

	public void setSamllJavaType(String javaType) {
		if (javaType.equals("Long")) {
			samllJavaType = "long";
		} else if (javaType.equals("Integer")) {
			samllJavaType = "int";
		} else {
			samllJavaType = javaType;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("javaName:" + javaName + ", dbName:" + dbColumnName);
		sb.append(", dbType:" + dbColumnType + ", javaType:" + javaType);
		sb.append(", dbLength:" + dbLength + ", canNull:" + canNull);
		sb.append(", primaryKey:" + primaryKey );
		return sb.toString();
	}

}
