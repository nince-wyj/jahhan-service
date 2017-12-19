package net.jahhan.codecenter.dao.abstrdao;

import java.sql.SQLException;
import java.util.List;

import net.jahhan.codecenter.pojo.TService;
import net.jahhan.codecenter.pojo.page.TServicePage;
import net.jahhan.jdbc.pojo.page.PagedResult;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
public interface AbstrTServiceDao
{
   /**
	* 增加 
	*/
	int addTService(TService tService);
	
	/**
	* 批量增加 
	*/
	int addBatchTService(List<TService> tServiceList);
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	int resetAllTService(long serviceId,TService tService);
	
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/	
	int updatePartTService(long serviceId,TService tService);	
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  serviceIds 主键列表
	 * @param TService conditionTService 旧值
	 * @param TService newTService 新值
	 */
	int updatePartByIds(List<Long> serviceIds,TServicePage conditionTService,TService newTService);
	
   /**
	* 删除 
	*/
	int delTService(long serviceId);
	
	/** 
	 * 加载
	 * @throws SQLException
	 */
	TService queryTService(long serviceId);
	
	/**
	 * 根据主键Id列出对象，不保证记录的顺序
	 * @param ids 以逗号分隔的id列表
	 * @return
	 */
	List<TService> listTServiceByIds(List<Long> serviceIds);
	
	/**
	 * 加载指定条件的所有id
	 */
	List<Long> listIds(TServicePage tService);
	
	/** 分页查询 **/
	public PagedResult<TService> pagedResultOfListTService(TServicePage tService);
	
	
	/**
	 * 批量删除
	 */	
	int delByIds(List<Long> serviceIds);
	
	/**
	 * 获取最大主键
	 */		
	long getMaxSequence();
}
