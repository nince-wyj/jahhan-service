package net.jahhan.codecenter.dao.abstrdao;

import java.sql.SQLException;
import java.util.List;

import net.jahhan.codecenter.pojo.TDb;
import net.jahhan.codecenter.pojo.page.TDbPage;
import net.jahhan.jdbc.pojo.page.PagedResult;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
public interface AbstrTDbDao
{
   /**
	* 增加 
	*/
	int addTDb(TDb tDb);
	
	/**
	* 批量增加 
	*/
	int addBatchTDb(List<TDb> tDbList);
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	int resetAllTDb(long dbId,TDb tDb);
	
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/	
	int updatePartTDb(long dbId,TDb tDb);	
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  dbIds 主键列表
	 * @param TDb conditionTDb 旧值
	 * @param TDb newTDb 新值
	 */
	int updatePartByIds(List<Long> dbIds,TDbPage conditionTDb,TDb newTDb);
	
   /**
	* 删除 
	*/
	int delTDb(long dbId);
	
	/** 
	 * 加载
	 * @throws SQLException
	 */
	TDb queryTDb(long dbId);
	
	/**
	 * 根据主键Id列出对象，不保证记录的顺序
	 * @param ids 以逗号分隔的id列表
	 * @return
	 */
	List<TDb> listTDbByIds(List<Long> dbIds);
	
	/**
	 * 加载指定条件的所有id
	 */
	List<Long> listIds(TDbPage tDb);
	
	/** 分页查询 **/
	public PagedResult<TDb> pagedResultOfListTDb(TDbPage tDb);
	
	
	/**
	 * 批量删除
	 */	
	int delByIds(List<Long> dbIds);
	
	/**
	 * 获取最大主键
	 */		
	long getMaxSequence();
}
