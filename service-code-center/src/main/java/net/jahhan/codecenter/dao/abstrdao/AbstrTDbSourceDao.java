package net.jahhan.codecenter.dao.abstrdao;

import java.sql.SQLException;
import java.util.List;

import net.jahhan.codecenter.pojo.TDbSource;
import net.jahhan.codecenter.pojo.page.TDbSourcePage;
import net.jahhan.jdbc.pojo.page.PagedResult;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
public interface AbstrTDbSourceDao
{
   /**
	* 增加 
	*/
	int addTDbSource(TDbSource tDbSource);
	
	/**
	* 批量增加 
	*/
	int addBatchTDbSource(List<TDbSource> tDbSourceList);
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	int resetAllTDbSource(long sourceId,TDbSource tDbSource);
	
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/	
	int updatePartTDbSource(long sourceId,TDbSource tDbSource);	
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  sourceIds 主键列表
	 * @param TDbSource conditionTDbSource 旧值
	 * @param TDbSource newTDbSource 新值
	 */
	int updatePartByIds(List<Long> sourceIds,TDbSourcePage conditionTDbSource,TDbSource newTDbSource);
	
   /**
	* 删除 
	*/
	int delTDbSource(long sourceId);
	
	/** 
	 * 加载
	 * @throws SQLException
	 */
	TDbSource queryTDbSource(long sourceId);
	
	/**
	 * 根据主键Id列出对象，不保证记录的顺序
	 * @param ids 以逗号分隔的id列表
	 * @return
	 */
	List<TDbSource> listTDbSourceByIds(List<Long> sourceIds);
	
	/**
	 * 加载指定条件的所有id
	 */
	List<Long> listIds(TDbSourcePage tDbSource);
	
	/** 分页查询 **/
	public PagedResult<TDbSource> pagedResultOfListTDbSource(TDbSourcePage tDbSource);
	
	
	/**
	 * 批量删除
	 */	
	int delByIds(List<Long> sourceIds);
	
	/**
	 * 获取最大主键
	 */		
	long getMaxSequence();
}
