package net.jahhan.codecenter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取表信息
 */
@Data
public class TableInfo implements Serializable {
	private static final long serialVersionUID = -5944026695379518264L;

	@ApiModelProperty(value = "表属于哪个数据源")
	private String dbSource;

	@ApiModelProperty(value = "数据库中表名称")
	private String dbTableName;

	@ApiModelProperty(value = "对应的类名称")
	private String className;

	@ApiModelProperty(value = "表备注")
	private String comment;

	@ApiModelProperty(value = "表注释注解集")
	private List<String> tableNote = null;

	@ApiModelProperty(value = "表中的的字段")
	private List<ColumnInfo> columnInfoList;

	@ApiModelProperty(value = "主键")
	private String pramiryKey = null;

//	@ApiModelProperty(value = "包路径")
//	private String codePackage;
//
//	@ApiModelProperty(value = "服务名")
//	private String serviceName;
	
	@ApiModelProperty(value = "主键是否自增")
	private boolean autoInc = true;

	@ApiModelProperty(value = "表属性")
	private Map<String, String> tablePara = new ConcurrentHashMap<>();

//	@ApiModelProperty(value = "代码属性")
//	private Map<String, String> codePara = new ConcurrentHashMap<>();

	public void setKeyColumnInfoList(List<String> keyColumnInfoList) {
		ColumnInfo columnInfo = null;
		for (String keyName : keyColumnInfoList) {
			columnInfo = getColumnByDbName(keyName);
			columnInfo.setPrimaryKey(true);
		}
	}

	/**
	 * 获取主键数目
	 * 
	 * @return
	 */
	public int getKeyCount() {
		return this.getKeyColumns().size();
	}

	/**
	 * 获取主键
	 * 
	 * @return
	 */
	public List<ColumnInfo> getKeyColumns() {
		List<ColumnInfo> keyList = new ArrayList<ColumnInfo>();
		for (int i = 0; i < this.columnInfoList.size(); i++) {
			if (this.columnInfoList.get(i).isPrimaryKey())
				keyList.add(this.columnInfoList.get(i));
		}
		return keyList;
	}

	/**
	 * 
	 * @return
	 */
	public ColumnInfo getSingleKey() {
		for (int i = 0; i < this.columnInfoList.size(); i++) {
			if (this.columnInfoList.get(i).isPrimaryKey())
				return this.columnInfoList.get(i);
		}
		return null;
	}

	/**
	 * 获取非空的列，不包含主键
	 * 
	 * @return
	 */
	public List<ColumnInfo> getNoNullColumns() {
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();
		for (int i = 0; i < this.columnInfoList.size(); i++) {
			ColumnInfo col = this.columnInfoList.get(i);
			if (col.isCanNull() || col.isPrimaryKey()) {
				continue;
			}
			list.add(col);
		}
		return list;
	}

	/**
	 * 获取非主键列表
	 * 
	 * @return
	 */
	public List<ColumnInfo> getNonKeyColumns() {
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();
		for (int i = 0; i < this.columnInfoList.size(); i++) {
			if (!this.columnInfoList.get(i).isPrimaryKey())
				list.add(this.columnInfoList.get(i));
		}
		return list;
	}

	/**
	 * 根据数据库的字段名获取ColumnInfo，大小写不敏感
	 * 
	 * @param dbName
	 *            数据库的字段名
	 * @return
	 */
	public ColumnInfo getColumnByDbName(String dbName) {
		for (ColumnInfo info : this.columnInfoList) {
			if (info.getDbColumnName().equalsIgnoreCase(dbName))
				return info;
		}
		return null;
	}

	/**
	 * 根据java类的字段名的字段名获取ColumnInfo，大小写不敏感
	 * 
	 * @param dbName
	 *            数据库的字段名
	 * @return
	 */
	public ColumnInfo getColumnByJavaName(String javaName) {
		for (ColumnInfo info : this.columnInfoList) {
			if (info.getJavaName().equalsIgnoreCase(javaName))
				return info;
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("dbTableName:" + dbTableName + ", class:" + className + "\n");
		sb.append(columnInfoList.toString());
		sb.append("\n");
		return sb.toString();
	}
}
