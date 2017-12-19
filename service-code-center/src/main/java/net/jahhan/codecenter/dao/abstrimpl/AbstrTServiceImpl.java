package net.jahhan.codecenter.dao.abstrimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import net.jahhan.codecenter.pojo.TService;
import net.jahhan.codecenter.dao.TServiceDao;
import net.jahhan.codecenter.pojo.page.TServicePage;
import net.jahhan.codecenter.dao.listen.TServiceRep;

import lombok.extern.slf4j.Slf4j;
import net.jahhan.cache.repository.common.SeqRepository;
import net.jahhan.cache.util.SerializerUtil;
import net.jahhan.common.extension.utils.ListUtils;
import net.jahhan.jdbc.context.DBContext;
import net.jahhan.jdbc.context.DBVariable;
import net.jahhan.jdbc.event.EventOperate;
import net.jahhan.jdbc.pojo.page.PagedResult;
import net.jahhan.jdbc.utils.ValidationUtil;
import net.jahhan.jdbc.validategroup.Create;
import net.jahhan.jdbc.validategroup.Modify;

/*
 * 自动生成,开发人员请勿修改.
 * 
 * @author code-generate-service
 */
 @Slf4j(topic = "dao.TServiceDao")
public abstract class AbstrTServiceImpl implements TServiceDao {

	protected String dataSource = "code_center";
	@Inject
	private TServiceRep repository;
	@Inject
	protected DBContext dbContext;
	
	/**
	 * 是否使用缓存
	 * @return true表示使用缓存，false不使用
	 */
	protected abstract boolean isCachable();
	
	/**
	 * 缓存主键
	 */
	protected String pkToString(TService tService){
		return ((Number)tService.getServiceId()).toString();
	}
	
	/** 增加  **/
	public int addTService(TService tService){
		tService.setServiceId(SeqRepository.inc("seq_t_service"));
		tService.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tService, Create.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.insert("TSERVICE.addTService", tService);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.INSERT, pkToString(tService));
		}
		return ret;
	}
	
	/** 批量增加 **/
	public int addBatchTService(List<TService> tServiceList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TService tService : tServiceList) {
			tService.setServiceId(SeqRepository.inc("seq_t_service"));
			tService.setModifyTimestamp(System.currentTimeMillis());
			addBatchTService(session, tService);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void addBatchTService(SqlSession session, TService tService) {
		ValidationUtil.validate(tService, Create.class);
		int ret = session.insert("TSERVICE.addTService", tService);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.INSERT,
					pkToString(tService));
		}
	}
	
	/** 删除 **/
	public int delTService(long serviceId){
		TService tService=new TService();
		tService.setServiceId(serviceId);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.delete("TSERVICE.delTService", tService);
		if(ret > 0){
			dbContext.publishDeleteEvent(dataSource, session.getConnection(), tService, pkToString(tService));
		}
		return ret;
		
	}
	
	/** 根据主键列表批量删除**/
	public int delByIds(List<Long> serviceIds){
		if(null!=serviceIds&&serviceIds.size()>0){
			SqlSession session = dbContext.getWriteSession(dataSource);
			int ret = session.delete("TSERVICE.delTServiceByIds", serviceIds);
			if(ret > 0){
				for(Long serviceId:serviceIds){
					TService tService=new TService();
					tService.setServiceId(serviceId);
					dbContext.publishDeleteEvent(dataSource, session.getConnection(), tService, pkToString(tService));
				}
			}
			return ret;
		}
		log.debug("serviceIds is empty!");
		return 0;
	}
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	public int resetAllTService(long serviceId,TService tService){
	    tService.setServiceId(serviceId);
		tService.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICE.updateTService", tService);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.UPDATE, pkToString(tService));
		}
		return ret;
	}
	
   /**
	* 全更新，注意为Null的属生也会更新 
	*/
	protected int resetAllTService(TService tService){
		tService.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICE.updateTService", tService);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.UPDATE, pkToString(tService));
		}
		return ret;
	}
	
	/** 批量全更新 **/
	protected int resetBatchTService(List<TService> tServiceList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TService tService : tServiceList) {
			tService.setModifyTimestamp(System.currentTimeMillis());
			resetBatchTService(session, tService);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void resetBatchTService(SqlSession session, TService tService) {
		int ret = session.update("TSERVICE.updateTService", tService);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.UPDATE, pkToString(tService));
		}
	}
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/		
	public int updatePartTService(long serviceId,TService tService){
	    tService.setServiceId(serviceId);
		tService.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tService, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICE.updatePartTService", tService);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.PART_MODIFY, pkToString(tService));
		}
		return ret;
	}
	
   /**
	* 部分更新,属性不为Null的会更新
	*/	
	protected int updatePartTService(TService tService){
		tService.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tService, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICE.updatePartTService", tService);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.PART_MODIFY, pkToString(tService));
		}
		return ret;
	}

	/** 批量更新 **/
	protected int updatePartBatchTService(List<TService> tServiceList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TService tService : tServiceList) {
			tService.setModifyTimestamp(System.currentTimeMillis());
			ValidationUtil.validate(tService, Modify.class);
			updatePartBatchTService(dataSource, session, tService);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void updatePartBatchTService(String dataSource, SqlSession session, TService tService) {
		int ret = session.update("TSERVICE.updatePartTService", tService);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.PART_MODIFY, pkToString(tService));
		}
	}
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  serviceIds 主键列表
	 * @param TService conditionTService 旧值(条件)
	 * @param TService newTService 新值
	 */
	public int updatePartByIds(List<Long> serviceIds,TServicePage conditionTService,TService newTService){
		newTService.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(newTService, Modify.class);
		Map<String,Object> map=new HashMap<>();
		if(CollectionUtils.isEmpty(serviceIds)){
			log.debug("serviceIds is empty!");
			return 0;
		}
		map.put("ids", serviceIds);
		if(conditionTService!=null){
			conditionTService.setServiceId(null);
			map.put("oldObj", conditionTService);
		}
		map.put("newObj", newTService);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICE.updatePartTServiceByIds", map);
		if(ret > 0){
			TService tService=newTService;
			for(Long serviceId:serviceIds){
				tService.setServiceId(serviceId);
				dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tService, EventOperate.OTHER_MODIFY, pkToString(tService));
			}
		}
		return ret;
	}
	
	/** 加载 **/
	public TService queryTService(long serviceId){
		DBVariable invocationContext = DBVariable.getDBVariable();
		TService localCache = (TService) invocationContext.getLocalCachePojo(TService.class,
				String.valueOf(serviceId));
		if (null != localCache) {
			return localCache;
		}
		if (isCachable() && !invocationContext.isDeletePojo(TService.class, String.valueOf(serviceId))) {
			byte[] bytes = repository.getBytes(String.valueOf(serviceId));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, TService.class);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("queryTService {} from db",serviceId);
		}
		TServicePage tService=new TServicePage();
		tService.setServiceId(serviceId);
		SqlSession session = dbContext.getReadSession(dataSource);
		TService retVal = session.selectOne("TSERVICE.queryTService", tService);
		if(retVal != null){
			dbContext.publishReadPojo(dataSource, retVal,String.valueOf(retVal.getServiceId()));
		}
		return retVal;
    }
	
	/** 查询 **/
	protected List<TService> listTService(TServicePage tService){
	    tService.setPageSize(tService.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TService > list = session.selectList("TSERVICE.listTService", tService);
		dbContext.publishReadList(dataSource, TService.class,tService, list);
		tService.setPageSize(tService.getPageSize() - 1);
		if(list.size() > tService.getPageSize()){
		   tService.setNextPage(true);
		   list.remove((int)tService.getPageSize());
		}
		else{
		   tService.setNextPage(false);
		}
		return list;
	}
	
	
	/** 查询数量 **/
	protected long countTService(TServicePage tService){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("TSERVICE.countTService",  tService);
		dbContext.publishReadCount(dataSource, TService.class, tService,count);
		return count;
	}
	
	
	/** 分页查询 **/
	public PagedResult<TService> pagedResultOfListTService(
			TServicePage tService) {
		PagedResult<TService> result = new PagedResult<>();
		result.setList(listTService(tService));
		result.setHasNextPage(tService.isNextPage());
		result.setCount(countTService(tService));
		return result;
	}
	
	
	public List<TService> listTServiceByIdsFromDB(List<Long> serviceIds){
		if(CollectionUtils.isEmpty(serviceIds)){
			return new ArrayList<TService>();
		}
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TService> list = session.selectList("TSERVICE.listTServiceByIds",  serviceIds);
		dbContext.publishReadList(dataSource, TService.class,serviceIds, list);
		return list;
	}

	public List<TService> listTServiceByIds(List<Long> serviceIds){
		if (CollectionUtils.isEmpty(serviceIds)) {
			return new ArrayList<TService>();
		}
		DBVariable invocationContext = DBVariable.getDBVariable();
		List<Long> hitList = new ArrayList<>();
		List<TService> localCacheList = new ArrayList<>();
		for (Long serviceId : serviceIds) {
			TService localCache = (TService) invocationContext.getLocalCachePojo(TService.class,
					String.valueOf(serviceId));
			if (null != localCache) {
				hitList.add(serviceId);
				localCacheList.add(localCache);
			}
		}
		serviceIds.removeAll(hitList);
		// 如果不走缓存，就直接从数据库中取
		if (!this.isCachable()) {
			List<TService> dbList = listTServiceByIdsFromDB(serviceIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<byte[]> values = repository.getMultiByteValue(ListUtils.list2ByteList(serviceIds));
		// redis访问失败，就从数据库中读
		if (null == values) {
			List<TService> dbList = listTServiceByIdsFromDB(serviceIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<TService> retList = new ArrayList<TService>(serviceIds.size());
		List<Long> notCachedidList = new ArrayList<>(serviceIds.size());
		// 缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for (int i = 0; i < values.size(); i++) {
			byte[] value = values.get(i);
			if (null == value) {
				notCachedidList.add(serviceIds.get(i));
			} else {
				retList.add(SerializerUtil.deserialize(value, TService.class));
			}
		}
		// 全部都在缓存中
		if (notCachedidList.isEmpty()) {
			retList.addAll(localCacheList);
			return retList;
		}

		// 从数据库中获取缓存中没有的对象
		List<TService> objs = this.listTServiceByIdsFromDB(notCachedidList);
		// 根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		retList.addAll(localCacheList);
		return retList;
	}
	
	/*
	 * 加载指定条件的所有id
	 * @param TServicePage tService
	 */
	public List<Long> listIds(TServicePage tService){
	    tService.setPageSize(tService.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<Long> list = session.selectList("TSERVICE.listIds", tService);
		dbContext.publishReadList(dataSource, TService.class,tService, list);
		tService.setPageSize(tService.getPageSize() - 1);
		if(list.size() > tService.getPageSize()){
		   tService.setNextPage(true);
		   list.remove((int)tService.getPageSize());
		}
		else{
		   tService.setNextPage(false);
		}		
		return list;
	}
	
   /**
	* 获取最大主键
	*/		
	public long getMaxSequence(){
		SqlSession session = dbContext.getReadSession(dataSource);
		long maxSequence = session.selectOne("TSERVICE.selectMaxTServiceSequence");
		return maxSequence;	  
	}
}
