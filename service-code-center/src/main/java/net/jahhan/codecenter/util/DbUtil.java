package net.jahhan.codecenter.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.jahhan.codecenter.bean.ColumnInfo;
import net.jahhan.codecenter.bean.TableInfo;
import net.jahhan.codecenter.constant.DbParam;

@Slf4j
public class DbUtil {

	/**
	 * 创建表
	 * 
	 * @param sql
	 * @param dParam
	 */
	public static void createTableBySql(String sql, DbParam dParam) {
		Statement st = null;
		Connection conn = null;
		conn = DbConnection.getConnection(dParam);
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(st);
			DbConnection.close(conn);
		}
	}

	/**
	 * 获得一个表的外键信息
	 * 
	 * @param dbMetaData
	 * @param schemaName
	 * @param tableName
	 */
	public void getAllExportedKeys(DatabaseMetaData dbMetaData, String schemaName, String tableName) {
		ResultSet rs = null;
		try {
			rs = dbMetaData.getExportedKeys(null, schemaName, tableName);
			while (rs.next()) {
				String pkTableCat = rs.getString("PKTABLE_CAT");// 主键表的目录（可能为空）
				String pkTableSchem = rs.getString("PKTABLE_SCHEM");// 主键表的架构（可能为空）
				String pkTableName = rs.getString("PKTABLE_NAME");// 主键表名
				String pkColumnName = rs.getString("PKCOLUMN_NAME");// 主键列名
				String fkTableCat = rs.getString("FKTABLE_CAT");// 外键的表的目录（可能为空）出口（可能为null）
				String fkTableSchem = rs.getString("FKTABLE_SCHEM");// 外键表的架构（可能为空）出口（可能为空）
				String fkTableName = rs.getString("FKTABLE_NAME");// 外键表名
				String fkColumnName = rs.getString("FKCOLUMN_NAME"); // 外键列名
				short keySeq = rs.getShort("KEY_SEQ");// 序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。
				short updateRule = rs.getShort("UPDATE_RULE");
				short delRule = rs.getShort("DELETE_RULE");
				String fkName = rs.getString("FK_NAME");// 外键的名称（可能为空）
				String pkName = rs.getString("PK_NAME");// 主键的名称（可能为空）
				short deferRability = rs.getShort("DEFERRABILITY");

				log.info("获得一个表的外键信息" + pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-"
						+ fkTableCat + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-"
						+ updateRule + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs);
		}
	}

	/**
	 * 获得一个表的主键信息
	 * 
	 * @param dbMetaData
	 * @param schemaName
	 * @param tableName
	 * @return
	 */
	public static List<String> getAllPrimaryKeys(DatabaseMetaData dbMetaData, String dbName, String tableName) {
		List<String> keyColumnList = new ArrayList<String>();
		ResultSet rs = null;
		try {
			rs = dbMetaData.getPrimaryKeys(null, dbName, tableName);
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");// 列名
				short keySeq = rs.getShort("KEY_SEQ");// 序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
				String pkName = rs.getString("PK_NAME"); // 主键名称
				keyColumnList.add(columnName);
				log.info("获得一个表的主键信息" + columnName + "-" + keySeq + "-" + pkName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs);
		}
		return keyColumnList;
	}

	/**
	 * 获得一个表的索引信息
	 * 
	 * @param dbMetaData
	 * @param schemaName
	 * @param tableName
	 */
	public void getIndexInfo(DatabaseMetaData dbMetaData, String dbName, String tableName) {
		ResultSet rs = null;
		try {
			rs = dbMetaData.getIndexInfo(null, dbName, tableName, true, true);
			while (rs.next()) {
				boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 非唯一索引
				String indexQualifier = rs.getString("INDEX_QUALIFIER");// 索引目录（可能为空）
				String indexName = rs.getString("INDEX_NAME");// 索引的名称
				short type = rs.getShort("TYPE");// 索引类型
				short ordinalPosition = rs.getShort("ORDINAL_POSITION");// 在索引列顺序号
				String columnName = rs.getString("COLUMN_NAME");// 列名
				String ascOrDesc = rs.getString("ASC_OR_DESC");// 列排序顺序:升序还是降序
				int cardinality = rs.getInt("CARDINALITY"); // 基数
				log.info("DbUtil获得一个表的索引信息" + nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-"
						+ ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs);
		}
	}

	/**
	 * 获得表中所有列信息
	 * 
	 * @param dbMetaData
	 * @param schemaName
	 * @param tableName
	 * @return
	 */
	public static List<ColumnInfo> getTableColumns(DatabaseMetaData dbMetaData, String dbName, String tableName) {
		List<ColumnInfo> columnInfoList = new ArrayList<ColumnInfo>();
		ResultSet rs = null;
		ColumnInfo columnInfo = null;
		try {

			rs = dbMetaData.getColumns(null, dbName, tableName, "%");
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				columnInfo = new ColumnInfo();
				String tableCat = rs.getString("TABLE_CAT");// 表目录（可能为空）
				String tableSchemaName = rs.getString("TABLE_SCHEM");// 表的架构（可能为空）
				String tableName_ = rs.getString("TABLE_NAME");// 表名
				String cName = rs.getString("COLUMN_NAME");// 列名
				int dataType = rs.getInt("DATA_TYPE"); // 对应的java.sql.Types类型
				String dataTypeName = rs.getString("TYPE_NAME");// java.sql.Types类型
																// 名称
				int columnSize = rs.getInt("COLUMN_SIZE");// 列大小
				int decimalDigits = rs.getInt("DECIMAL_DIGITS");// 小数位数
				int numPrecRadix = rs.getInt("NUM_PREC_RADIX");// 基数（通常是10或2）
				int nullAble = rs.getInt("NULLABLE");// 是否允许为null
				String remarks = rs.getString("REMARKS");// 列描述
				String columnDef = rs.getString("COLUMN_DEF");// 默认值
				int sqlDataType = rs.getInt("SQL_DATA_TYPE");// sql数据类型
				int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB"); // SQL日期时间分?
				int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH"); // char类型的列中的最大字节数
				int ordinalPosition = rs.getInt("ORDINAL_POSITION"); // 表中列的索引（从1开始）
				boolean isNullAble = rs.getBoolean("IS_NULLABLE");// 是否可以为空
				boolean isAutoincrement = rs.getBoolean("IS_AUTOINCREMENT");// 是否自动

				String columnName = "";
				int indexOfcolumn = cName.indexOf('@');
				if (indexOfcolumn < 0) {
					columnName = cName;
				}

				if (null != remarks) {
					String[] splitString = StringUtil.splitString(remarks, "@");

					columnInfo.setComment(splitString[0]);
					if (splitString.length > 1) {
						List<String> columnNote = new ArrayList<>();
						for (int i = 1; i < splitString.length; i++) {
							columnNote.add(splitString[i]);
						}
						columnInfo.setColumnNote(columnNote);
					}
				}
				if (null != columnDef) {
					columnInfo.setCanNull(true);
				} else {
					columnInfo.setCanNull(isNullAble);
				}
				columnInfo.setDbColumnName(columnName);
				columnInfo.setJavaName(StringUtil.makeJavaNameStr(columnName));
				columnInfo.setDbColumnType(dataTypeName);
				columnInfo.setDbLength(columnSize);
				columnInfoList.add(columnInfo);
				sb.append("表或视图中的列信息" + tableCat + "-" + tableSchemaName + "-" + tableName_ + "-" + columnName + "-"
						+ dataType + "-" + dataTypeName + "-" + columnSize + "-" + decimalDigits + "-" + numPrecRadix
						+ "-" + nullAble + "-" + remarks + "-" + columnDef + "-" + sqlDataType + "-" + sqlDatetimeSub
						+ charOctetLength + "-" + ordinalPosition + "-" + isNullAble + "-" + isAutoincrement + "-\r\n");
			}
			log.info(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs);
		}
		return columnInfoList;
	}

	/**
	 * 获取指定表的信息
	 * 
	 * @param dbMetaData
	 * @param dbName
	 * @param tableName
	 * @param types
	 * @return
	 */
	public static List<TableInfo> getAllTableInfo(DatabaseMetaData dbMetaData, String sourceName, String dbName,
			String[] types) {
		List<TableInfo> talbleNameList = new ArrayList<TableInfo>();
		ResultSet rs = null;
		try {
			rs = dbMetaData.getTables(null, dbName, "%", types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME"); // 表名
				String tableType = rs.getString("TABLE_TYPE"); // 表类型
				String remarks = rs.getString("REMARKS"); // 表备注

				TableInfo tableInfo = new TableInfo();
				tableInfo.setDbSource(sourceName);
				tableInfo.setDbTableName(tableName);
				if (null != remarks) {
					String[] splitString = StringUtil.splitString(remarks, "@");
					if (splitString.length > 1) {
						List<String> tableNote = new ArrayList<>();
						for (int i = 1; i < splitString.length; i++) {
							tableNote.add(splitString[i]);
						}
						tableInfo.setTableNote(tableNote);
					}

					String[] exString = StringUtil.splitString(splitString[0], "#");
					tableInfo.setComment(exString[0]);
					Map<String, String> tablePara = null;
					Map<String, String> tableParaMap = new HashMap<>();
					if (exString.length > 1) {
						for (int i = 1; i < exString.length; i++) {
							String[] split = exString[i].split(":");
							if (split.length > 1) {
								tableParaMap.put(split[0], split[1]);
							}
						}
					}
					tablePara = TableParaUtil.getTablePara(tableParaMap);
					tableInfo.setTablePara(tablePara);
				}

				tableInfo.setClassName(StringUtil.makeClassNameStr(tableName));
				log.info("表或视图信息" + "this schemaName " + dbName + " table :" + tableName + "-" + tableType + "-"
						+ remarks);
				talbleNameList.add(tableInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs);
		}
		return talbleNameList;
	}

	/**
	 * 获得数据库的一些相关信息
	 * 
	 * @param dbMetaData
	 */
	public static void printDataBaseInformations(DatabaseMetaData dbMetaData) {
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("获得数据库的一些相关信息\r\n");
			sb.append("数据库已知的用户:    " + dbMetaData.getUserName());
			sb.append("\r\n数据库的系统函数的逗号分隔列表:     " + dbMetaData.getSystemFunctions());
			sb.append("\r\n数据库的时间和日期函数的逗号分隔列表:     " + dbMetaData.getTimeDateFunctions());
			sb.append("\r\n数据库的字符串函数的逗号分隔列表:    " + dbMetaData.getStringFunctions());
			sb.append("\r\n数据库供应商用于 'schema' 的首选术语:     " + dbMetaData.getSchemaTerm());
			sb.append("\r\n数据库URL:    " + dbMetaData.getURL());
			sb.append("\r\n是否允许只读:    " + dbMetaData.isReadOnly());
			sb.append("\r\n数据库的产品名称:   " + dbMetaData.getDatabaseProductName());
			sb.append("\r\n数据库的版本:   " + dbMetaData.getDatabaseProductVersion());
			sb.append("\r\n驱动程序的名称:   " + dbMetaData.getDriverName());
			sb.append("\r\n驱动程序的版本:   " + dbMetaData.getDriverVersion());
			sb.append("\r\n数据库中使用的表类型\r\n");
			rs = dbMetaData.getTableTypes();
			while (rs.next()) {
				sb.append(rs.getString(1) + "\r\n");
			}
			log.info(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs);
		}
	}

	/**
	 * 获取数据库中所有的表
	 * 
	 * @return
	 */
	public static List<TableInfo> dbManager(String sourceName, String dbName, DbParam dParam) {

		Statement st = null;
		Connection conn = null;
		ResultSetMetaData rsmd = null;
		ResultSet rs = null;
		String sql = null;
		ColumnInfo columnInfo;// 数据库表字段信息
		int size;
		String type = null;
		String columnName;
		List<TableInfo> tableInfoList = null;
		try {
			conn = DbConnection.getConnection(dParam);
			DatabaseMetaData dmd = conn.getMetaData();
			st = conn.createStatement();

			// 打印数据库的一些相关信息
			printDataBaseInformations(dmd);
			tableInfoList = getAllTableInfo(dmd, sourceName, dbName, new String[] { "TABLE" });
			for (TableInfo tableInfo : tableInfoList) {
				tableInfo.setColumnInfoList(getTableColumns(dmd, dbName, tableInfo.getDbTableName()));
				tableInfo.setKeyColumnInfoList(getAllPrimaryKeys(dmd, dbName, tableInfo.getDbTableName()));

				sql = "SELECT * FROM " + tableInfo.getDbTableName() + " LIMIT  1";
				rs = st.executeQuery(sql);
				rsmd = rs.getMetaData();
				size = rsmd.getColumnCount();

				for (int i = 1; i < size + 1; i++) {
					columnName = rsmd.getColumnName(i);
					try {
						type = rsmd.getColumnClassName(i).split("\\.")[2];
						columnInfo = tableInfo.getColumnByDbName(columnName);
						columnInfo.setJavaType(type);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(rs, st, conn);
		}
		return tableInfoList;
	}

}
