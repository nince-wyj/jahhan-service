package net.jahhan.codecenter.dao.abstrdao;

import java.sql.SQLException;
import java.util.List;

import net.jahhan.codecenter.pojo.TServiceSourceConf;
import net.jahhan.codecenter.pojo.page.TServiceSourceConfPage;
import net.jahhan.jdbc.pojo.page.PagedResult;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
public interface AbstrTServiceSourceConfDao
{
   /**
	* 增加 
	*/
	int addTServiceSourceConf(TServiceSourceConf tServiceSourceConf);
	
	/**
	* 批量增加 
	*/
	int addBatchTServiceSourceConf(List<TServiceSourceConf> tServiceSourceConfList);
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	int resetAllTServiceSourceConf(long serviceSourceConfId,TServiceSourceConf tServiceSourceConf);
	
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/	
	int updatePartTServiceSourceConf(long serviceSourceConfId,TServiceSourceConf tServiceSourceConf);	
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  serviceSourceConfIds 主键列表
	 * @param TServiceSourceConf conditionTServiceSourceConf 旧值
	 * @param TServiceSourceConf newTServiceSourceConf 新值
	 */
	int updatePartByIds(List<Long> serviceSourceConfIds,TServiceSourceConfPage conditionTServiceSourceConf,TServiceSourceConf newTServiceSourceConf);
	
   /**
	* 删除 
	*/
	int delTServiceSourceConf(long serviceSourceConfId);
	
	/** 
	 * 加载
	 * @throws SQLException
	 */
	TServiceSourceConf queryTServiceSourceConf(long serviceSourceConfId);
	
	/**
	 * 根据主键Id列出对象，不保证记录的顺序
	 * @param ids 以逗号分隔的id列表
	 * @return
	 */
	List<TServiceSourceConf> listTServiceSourceConfByIds(List<Long> serviceSourceConfIds);
	
	/**
	 * 加载指定条件的所有id
	 */
	List<Long> listIds(TServiceSourceConfPage tServiceSourceConf);
	
	/** 分页查询 **/
	public PagedResult<TServiceSourceConf> pagedResultOfListTServiceSourceConf(TServiceSourceConfPage tServiceSourceConf);
	
	
	/**
	 * 批量删除
	 */	
	int delByIds(List<Long> serviceSourceConfIds);
	
	/**
	 * 获取最大主键
	 */		
	long getMaxSequence();
}
