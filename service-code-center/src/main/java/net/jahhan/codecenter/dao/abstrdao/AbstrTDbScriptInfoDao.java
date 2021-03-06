package net.jahhan.codecenter.dao.abstrdao;

import java.sql.SQLException;
import java.util.List;

import net.jahhan.codecenter.pojo.TDbScriptInfo;
import net.jahhan.codecenter.pojo.page.TDbScriptInfoPage;
import net.jahhan.jdbc.pojo.page.PagedResult;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
public interface AbstrTDbScriptInfoDao
{
   /**
	* 增加 
	*/
	int addTDbScriptInfo(TDbScriptInfo tDbScriptInfo);
	
	/**
	* 批量增加 
	*/
	int addBatchTDbScriptInfo(List<TDbScriptInfo> tDbScriptInfoList);
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	int resetAllTDbScriptInfo(long dbScriptInfoId,TDbScriptInfo tDbScriptInfo);
	
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/	
	int updatePartTDbScriptInfo(long dbScriptInfoId,TDbScriptInfo tDbScriptInfo);	
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  dbScriptInfoIds 主键列表
	 * @param TDbScriptInfo conditionTDbScriptInfo 旧值
	 * @param TDbScriptInfo newTDbScriptInfo 新值
	 */
	int updatePartByIds(List<Long> dbScriptInfoIds,TDbScriptInfoPage conditionTDbScriptInfo,TDbScriptInfo newTDbScriptInfo);
	
   /**
	* 删除 
	*/
	int delTDbScriptInfo(long dbScriptInfoId);
	
	/** 
	 * 加载
	 * @throws SQLException
	 */
	TDbScriptInfo queryTDbScriptInfo(long dbScriptInfoId);
	
	/**
	 * 根据主键Id列出对象，不保证记录的顺序
	 * @param ids 以逗号分隔的id列表
	 * @return
	 */
	List<TDbScriptInfo> listTDbScriptInfoByIds(List<Long> dbScriptInfoIds);
	
	/**
	 * 加载指定条件的所有id
	 */
	List<Long> listIds(TDbScriptInfoPage tDbScriptInfo);
	
	/** 分页查询 **/
	public PagedResult<TDbScriptInfo> pagedResultOfListTDbScriptInfo(TDbScriptInfoPage tDbScriptInfo);
	
	
	/**
	 * 批量删除
	 */	
	int delByIds(List<Long> dbScriptInfoIds);
	
	/**
	 * 获取最大主键
	 */		
	long getMaxSequence();
}
