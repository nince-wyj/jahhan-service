package net.jahhan.codecenter.dao.abstrimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import net.jahhan.codecenter.pojo.TServiceSourceConf;
import net.jahhan.codecenter.dao.TServiceSourceConfDao;
import net.jahhan.codecenter.pojo.page.TServiceSourceConfPage;
import net.jahhan.codecenter.dao.listen.TServiceSourceConfRep;

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
 @Slf4j(topic = "dao.TServiceSourceConfDao")
public abstract class AbstrTServiceSourceConfImpl implements TServiceSourceConfDao {

	protected String dataSource = "code_center";
	@Inject
	private TServiceSourceConfRep repository;
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
	protected String pkToString(TServiceSourceConf tServiceSourceConf){
		return ((Number)tServiceSourceConf.getServiceSourceConfId()).toString();
	}
	
	/** 增加  **/
	public int addTServiceSourceConf(TServiceSourceConf tServiceSourceConf){
		tServiceSourceConf.setServiceSourceConfId(SeqRepository.inc("seq_t_service_source_conf"));
		tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tServiceSourceConf, Create.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.insert("TSERVICESOURCECONF.addTServiceSourceConf", tServiceSourceConf);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.INSERT, pkToString(tServiceSourceConf));
		}
		return ret;
	}
	
	/** 批量增加 **/
	public int addBatchTServiceSourceConf(List<TServiceSourceConf> tServiceSourceConfList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TServiceSourceConf tServiceSourceConf : tServiceSourceConfList) {
			tServiceSourceConf.setServiceSourceConfId(SeqRepository.inc("seq_t_service_source_conf"));
			tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
			addBatchTServiceSourceConf(session, tServiceSourceConf);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void addBatchTServiceSourceConf(SqlSession session, TServiceSourceConf tServiceSourceConf) {
		ValidationUtil.validate(tServiceSourceConf, Create.class);
		int ret = session.insert("TSERVICESOURCECONF.addTServiceSourceConf", tServiceSourceConf);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.INSERT,
					pkToString(tServiceSourceConf));
		}
	}
	
	/** 删除 **/
	public int delTServiceSourceConf(long serviceSourceConfId){
		TServiceSourceConf tServiceSourceConf=new TServiceSourceConf();
		tServiceSourceConf.setServiceSourceConfId(serviceSourceConfId);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.delete("TSERVICESOURCECONF.delTServiceSourceConf", tServiceSourceConf);
		if(ret > 0){
			dbContext.publishDeleteEvent(dataSource, session.getConnection(), tServiceSourceConf, pkToString(tServiceSourceConf));
		}
		return ret;
		
	}
	
	/** 根据主键列表批量删除**/
	public int delByIds(List<Long> serviceSourceConfIds){
		if(null!=serviceSourceConfIds&&serviceSourceConfIds.size()>0){
			SqlSession session = dbContext.getWriteSession(dataSource);
			int ret = session.delete("TSERVICESOURCECONF.delTServiceSourceConfByIds", serviceSourceConfIds);
			if(ret > 0){
				for(Long serviceSourceConfId:serviceSourceConfIds){
					TServiceSourceConf tServiceSourceConf=new TServiceSourceConf();
					tServiceSourceConf.setServiceSourceConfId(serviceSourceConfId);
					dbContext.publishDeleteEvent(dataSource, session.getConnection(), tServiceSourceConf, pkToString(tServiceSourceConf));
				}
			}
			return ret;
		}
		log.debug("serviceSourceConfIds is empty!");
		return 0;
	}
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	public int resetAllTServiceSourceConf(long serviceSourceConfId,TServiceSourceConf tServiceSourceConf){
	    tServiceSourceConf.setServiceSourceConfId(serviceSourceConfId);
		tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICESOURCECONF.updateTServiceSourceConf", tServiceSourceConf);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.UPDATE, pkToString(tServiceSourceConf));
		}
		return ret;
	}
	
   /**
	* 全更新，注意为Null的属生也会更新 
	*/
	protected int resetAllTServiceSourceConf(TServiceSourceConf tServiceSourceConf){
		tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICESOURCECONF.updateTServiceSourceConf", tServiceSourceConf);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.UPDATE, pkToString(tServiceSourceConf));
		}
		return ret;
	}
	
	/** 批量全更新 **/
	protected int resetBatchTServiceSourceConf(List<TServiceSourceConf> tServiceSourceConfList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TServiceSourceConf tServiceSourceConf : tServiceSourceConfList) {
			tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
			resetBatchTServiceSourceConf(session, tServiceSourceConf);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void resetBatchTServiceSourceConf(SqlSession session, TServiceSourceConf tServiceSourceConf) {
		int ret = session.update("TSERVICESOURCECONF.updateTServiceSourceConf", tServiceSourceConf);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.UPDATE, pkToString(tServiceSourceConf));
		}
	}
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/		
	public int updatePartTServiceSourceConf(long serviceSourceConfId,TServiceSourceConf tServiceSourceConf){
	    tServiceSourceConf.setServiceSourceConfId(serviceSourceConfId);
		tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tServiceSourceConf, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICESOURCECONF.updatePartTServiceSourceConf", tServiceSourceConf);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.PART_MODIFY, pkToString(tServiceSourceConf));
		}
		return ret;
	}
	
   /**
	* 部分更新,属性不为Null的会更新
	*/	
	protected int updatePartTServiceSourceConf(TServiceSourceConf tServiceSourceConf){
		tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tServiceSourceConf, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICESOURCECONF.updatePartTServiceSourceConf", tServiceSourceConf);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.PART_MODIFY, pkToString(tServiceSourceConf));
		}
		return ret;
	}

	/** 批量更新 **/
	protected int updatePartBatchTServiceSourceConf(List<TServiceSourceConf> tServiceSourceConfList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TServiceSourceConf tServiceSourceConf : tServiceSourceConfList) {
			tServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
			ValidationUtil.validate(tServiceSourceConf, Modify.class);
			updatePartBatchTServiceSourceConf(dataSource, session, tServiceSourceConf);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void updatePartBatchTServiceSourceConf(String dataSource, SqlSession session, TServiceSourceConf tServiceSourceConf) {
		int ret = session.update("TSERVICESOURCECONF.updatePartTServiceSourceConf", tServiceSourceConf);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.PART_MODIFY, pkToString(tServiceSourceConf));
		}
	}
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  serviceSourceConfIds 主键列表
	 * @param TServiceSourceConf conditionTServiceSourceConf 旧值(条件)
	 * @param TServiceSourceConf newTServiceSourceConf 新值
	 */
	public int updatePartByIds(List<Long> serviceSourceConfIds,TServiceSourceConfPage conditionTServiceSourceConf,TServiceSourceConf newTServiceSourceConf){
		newTServiceSourceConf.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(newTServiceSourceConf, Modify.class);
		Map<String,Object> map=new HashMap<>();
		if(CollectionUtils.isEmpty(serviceSourceConfIds)){
			log.debug("serviceSourceConfIds is empty!");
			return 0;
		}
		map.put("ids", serviceSourceConfIds);
		if(conditionTServiceSourceConf!=null){
			conditionTServiceSourceConf.setServiceSourceConfId(null);
			map.put("oldObj", conditionTServiceSourceConf);
		}
		map.put("newObj", newTServiceSourceConf);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TSERVICESOURCECONF.updatePartTServiceSourceConfByIds", map);
		if(ret > 0){
			TServiceSourceConf tServiceSourceConf=newTServiceSourceConf;
			for(Long serviceSourceConfId:serviceSourceConfIds){
				tServiceSourceConf.setServiceSourceConfId(serviceSourceConfId);
				dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tServiceSourceConf, EventOperate.OTHER_MODIFY, pkToString(tServiceSourceConf));
			}
		}
		return ret;
	}
	
	/** 加载 **/
	public TServiceSourceConf queryTServiceSourceConf(long serviceSourceConfId){
		DBVariable invocationContext = DBVariable.getDBVariable();
		TServiceSourceConf localCache = (TServiceSourceConf) invocationContext.getLocalCachePojo(TServiceSourceConf.class,
				String.valueOf(serviceSourceConfId));
		if (null != localCache) {
			return localCache;
		}
		if (isCachable() && !invocationContext.isDeletePojo(TServiceSourceConf.class, String.valueOf(serviceSourceConfId))) {
			byte[] bytes = repository.getBytes(String.valueOf(serviceSourceConfId));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, TServiceSourceConf.class);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("queryTServiceSourceConf {} from db",serviceSourceConfId);
		}
		TServiceSourceConfPage tServiceSourceConf=new TServiceSourceConfPage();
		tServiceSourceConf.setServiceSourceConfId(serviceSourceConfId);
		SqlSession session = dbContext.getReadSession(dataSource);
		TServiceSourceConf retVal = session.selectOne("TSERVICESOURCECONF.queryTServiceSourceConf", tServiceSourceConf);
		if(retVal != null){
			dbContext.publishReadPojo(dataSource, retVal,String.valueOf(retVal.getServiceSourceConfId()));
		}
		return retVal;
    }
	
	/** 查询 **/
	protected List<TServiceSourceConf> listTServiceSourceConf(TServiceSourceConfPage tServiceSourceConf){
	    tServiceSourceConf.setPageSize(tServiceSourceConf.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TServiceSourceConf > list = session.selectList("TSERVICESOURCECONF.listTServiceSourceConf", tServiceSourceConf);
		dbContext.publishReadList(dataSource, TServiceSourceConf.class,tServiceSourceConf, list);
		tServiceSourceConf.setPageSize(tServiceSourceConf.getPageSize() - 1);
		if(list.size() > tServiceSourceConf.getPageSize()){
		   tServiceSourceConf.setNextPage(true);
		   list.remove((int)tServiceSourceConf.getPageSize());
		}
		else{
		   tServiceSourceConf.setNextPage(false);
		}
		return list;
	}
	
	
	/** 查询数量 **/
	protected long countTServiceSourceConf(TServiceSourceConfPage tServiceSourceConf){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("TSERVICESOURCECONF.countTServiceSourceConf",  tServiceSourceConf);
		dbContext.publishReadCount(dataSource, TServiceSourceConf.class, tServiceSourceConf,count);
		return count;
	}
	
	
	/** 分页查询 **/
	public PagedResult<TServiceSourceConf> pagedResultOfListTServiceSourceConf(
			TServiceSourceConfPage tServiceSourceConf) {
		PagedResult<TServiceSourceConf> result = new PagedResult<>();
		result.setList(listTServiceSourceConf(tServiceSourceConf));
		result.setHasNextPage(tServiceSourceConf.isNextPage());
		result.setCount(countTServiceSourceConf(tServiceSourceConf));
		return result;
	}
	
	
	public List<TServiceSourceConf> listTServiceSourceConfByIdsFromDB(List<Long> serviceSourceConfIds){
		if(CollectionUtils.isEmpty(serviceSourceConfIds)){
			return new ArrayList<TServiceSourceConf>();
		}
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TServiceSourceConf> list = session.selectList("TSERVICESOURCECONF.listTServiceSourceConfByIds",  serviceSourceConfIds);
		dbContext.publishReadList(dataSource, TServiceSourceConf.class,serviceSourceConfIds, list);
		return list;
	}

	public List<TServiceSourceConf> listTServiceSourceConfByIds(List<Long> serviceSourceConfIds){
		if (CollectionUtils.isEmpty(serviceSourceConfIds)) {
			return new ArrayList<TServiceSourceConf>();
		}
		DBVariable invocationContext = DBVariable.getDBVariable();
		List<Long> hitList = new ArrayList<>();
		List<TServiceSourceConf> localCacheList = new ArrayList<>();
		for (Long serviceSourceConfId : serviceSourceConfIds) {
			TServiceSourceConf localCache = (TServiceSourceConf) invocationContext.getLocalCachePojo(TServiceSourceConf.class,
					String.valueOf(serviceSourceConfId));
			if (null != localCache) {
				hitList.add(serviceSourceConfId);
				localCacheList.add(localCache);
			}
		}
		serviceSourceConfIds.removeAll(hitList);
		// 如果不走缓存，就直接从数据库中取
		if (!this.isCachable()) {
			List<TServiceSourceConf> dbList = listTServiceSourceConfByIdsFromDB(serviceSourceConfIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<byte[]> values = repository.getMultiByteValue(ListUtils.list2ByteList(serviceSourceConfIds));
		// redis访问失败，就从数据库中读
		if (null == values) {
			List<TServiceSourceConf> dbList = listTServiceSourceConfByIdsFromDB(serviceSourceConfIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<TServiceSourceConf> retList = new ArrayList<TServiceSourceConf>(serviceSourceConfIds.size());
		List<Long> notCachedidList = new ArrayList<>(serviceSourceConfIds.size());
		// 缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for (int i = 0; i < values.size(); i++) {
			byte[] value = values.get(i);
			if (null == value) {
				notCachedidList.add(serviceSourceConfIds.get(i));
			} else {
				retList.add(SerializerUtil.deserialize(value, TServiceSourceConf.class));
			}
		}
		// 全部都在缓存中
		if (notCachedidList.isEmpty()) {
			retList.addAll(localCacheList);
			return retList;
		}

		// 从数据库中获取缓存中没有的对象
		List<TServiceSourceConf> objs = this.listTServiceSourceConfByIdsFromDB(notCachedidList);
		// 根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		retList.addAll(localCacheList);
		return retList;
	}
	
	/*
	 * 加载指定条件的所有id
	 * @param TServiceSourceConfPage tServiceSourceConf
	 */
	public List<Long> listIds(TServiceSourceConfPage tServiceSourceConf){
	    tServiceSourceConf.setPageSize(tServiceSourceConf.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<Long> list = session.selectList("TSERVICESOURCECONF.listIds", tServiceSourceConf);
		dbContext.publishReadList(dataSource, TServiceSourceConf.class,tServiceSourceConf, list);
		tServiceSourceConf.setPageSize(tServiceSourceConf.getPageSize() - 1);
		if(list.size() > tServiceSourceConf.getPageSize()){
		   tServiceSourceConf.setNextPage(true);
		   list.remove((int)tServiceSourceConf.getPageSize());
		}
		else{
		   tServiceSourceConf.setNextPage(false);
		}		
		return list;
	}
	
   /**
	* 获取最大主键
	*/		
	public long getMaxSequence(){
		SqlSession session = dbContext.getReadSession(dataSource);
		long maxSequence = session.selectOne("TSERVICESOURCECONF.selectMaxTServiceSourceConfSequence");
		return maxSequence;	  
	}
}
