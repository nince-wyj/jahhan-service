package net.jahhan.codecenter.dao.abstrimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import net.jahhan.codecenter.pojo.TDbSource;
import net.jahhan.codecenter.dao.TDbSourceDao;
import net.jahhan.codecenter.pojo.page.TDbSourcePage;
import net.jahhan.codecenter.dao.listen.TDbSourceRep;

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
 @Slf4j(topic = "dao.TDbSourceDao")
public abstract class AbstrTDbSourceImpl implements TDbSourceDao {

	protected String dataSource = "code_center";
	@Inject
	private TDbSourceRep repository;
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
	protected String pkToString(TDbSource tDbSource){
		return ((Number)tDbSource.getSourceId()).toString();
	}
	
	/** 增加  **/
	public int addTDbSource(TDbSource tDbSource){
		tDbSource.setSourceId(SeqRepository.inc("seq_t_db_source"));
		tDbSource.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDbSource, Create.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.insert("TDBSOURCE.addTDbSource", tDbSource);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.INSERT, pkToString(tDbSource));
		}
		return ret;
	}
	
	/** 批量增加 **/
	public int addBatchTDbSource(List<TDbSource> tDbSourceList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDbSource tDbSource : tDbSourceList) {
			tDbSource.setSourceId(SeqRepository.inc("seq_t_db_source"));
			tDbSource.setModifyTimestamp(System.currentTimeMillis());
			addBatchTDbSource(session, tDbSource);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void addBatchTDbSource(SqlSession session, TDbSource tDbSource) {
		ValidationUtil.validate(tDbSource, Create.class);
		int ret = session.insert("TDBSOURCE.addTDbSource", tDbSource);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.INSERT,
					pkToString(tDbSource));
		}
	}
	
	/** 删除 **/
	public int delTDbSource(long sourceId){
		TDbSource tDbSource=new TDbSource();
		tDbSource.setSourceId(sourceId);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.delete("TDBSOURCE.delTDbSource", tDbSource);
		if(ret > 0){
			dbContext.publishDeleteEvent(dataSource, session.getConnection(), tDbSource, pkToString(tDbSource));
		}
		return ret;
		
	}
	
	/** 根据主键列表批量删除**/
	public int delByIds(List<Long> sourceIds){
		if(null!=sourceIds&&sourceIds.size()>0){
			SqlSession session = dbContext.getWriteSession(dataSource);
			int ret = session.delete("TDBSOURCE.delTDbSourceByIds", sourceIds);
			if(ret > 0){
				for(Long sourceId:sourceIds){
					TDbSource tDbSource=new TDbSource();
					tDbSource.setSourceId(sourceId);
					dbContext.publishDeleteEvent(dataSource, session.getConnection(), tDbSource, pkToString(tDbSource));
				}
			}
			return ret;
		}
		log.debug("sourceIds is empty!");
		return 0;
	}
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	public int resetAllTDbSource(long sourceId,TDbSource tDbSource){
	    tDbSource.setSourceId(sourceId);
		tDbSource.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSOURCE.updateTDbSource", tDbSource);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.UPDATE, pkToString(tDbSource));
		}
		return ret;
	}
	
   /**
	* 全更新，注意为Null的属生也会更新 
	*/
	protected int resetAllTDbSource(TDbSource tDbSource){
		tDbSource.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSOURCE.updateTDbSource", tDbSource);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.UPDATE, pkToString(tDbSource));
		}
		return ret;
	}
	
	/** 批量全更新 **/
	protected int resetBatchTDbSource(List<TDbSource> tDbSourceList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDbSource tDbSource : tDbSourceList) {
			tDbSource.setModifyTimestamp(System.currentTimeMillis());
			resetBatchTDbSource(session, tDbSource);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void resetBatchTDbSource(SqlSession session, TDbSource tDbSource) {
		int ret = session.update("TDBSOURCE.updateTDbSource", tDbSource);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.UPDATE, pkToString(tDbSource));
		}
	}
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/		
	public int updatePartTDbSource(long sourceId,TDbSource tDbSource){
	    tDbSource.setSourceId(sourceId);
		tDbSource.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDbSource, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSOURCE.updatePartTDbSource", tDbSource);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.PART_MODIFY, pkToString(tDbSource));
		}
		return ret;
	}
	
   /**
	* 部分更新,属性不为Null的会更新
	*/	
	protected int updatePartTDbSource(TDbSource tDbSource){
		tDbSource.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDbSource, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSOURCE.updatePartTDbSource", tDbSource);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.PART_MODIFY, pkToString(tDbSource));
		}
		return ret;
	}

	/** 批量更新 **/
	protected int updatePartBatchTDbSource(List<TDbSource> tDbSourceList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDbSource tDbSource : tDbSourceList) {
			tDbSource.setModifyTimestamp(System.currentTimeMillis());
			ValidationUtil.validate(tDbSource, Modify.class);
			updatePartBatchTDbSource(dataSource, session, tDbSource);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void updatePartBatchTDbSource(String dataSource, SqlSession session, TDbSource tDbSource) {
		int ret = session.update("TDBSOURCE.updatePartTDbSource", tDbSource);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.PART_MODIFY, pkToString(tDbSource));
		}
	}
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  sourceIds 主键列表
	 * @param TDbSource conditionTDbSource 旧值(条件)
	 * @param TDbSource newTDbSource 新值
	 */
	public int updatePartByIds(List<Long> sourceIds,TDbSourcePage conditionTDbSource,TDbSource newTDbSource){
		newTDbSource.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(newTDbSource, Modify.class);
		Map<String,Object> map=new HashMap<>();
		if(CollectionUtils.isEmpty(sourceIds)){
			log.debug("sourceIds is empty!");
			return 0;
		}
		map.put("ids", sourceIds);
		if(conditionTDbSource!=null){
			conditionTDbSource.setSourceId(null);
			map.put("oldObj", conditionTDbSource);
		}
		map.put("newObj", newTDbSource);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSOURCE.updatePartTDbSourceByIds", map);
		if(ret > 0){
			TDbSource tDbSource=newTDbSource;
			for(Long sourceId:sourceIds){
				tDbSource.setSourceId(sourceId);
				dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbSource, EventOperate.OTHER_MODIFY, pkToString(tDbSource));
			}
		}
		return ret;
	}
	
	/** 加载 **/
	public TDbSource queryTDbSource(long sourceId){
		DBVariable invocationContext = DBVariable.getDBVariable();
		TDbSource localCache = (TDbSource) invocationContext.getLocalCachePojo(TDbSource.class,
				String.valueOf(sourceId));
		if (null != localCache) {
			return localCache;
		}
		if (isCachable() && !invocationContext.isDeletePojo(TDbSource.class, String.valueOf(sourceId))) {
			byte[] bytes = repository.getBytes(String.valueOf(sourceId));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, TDbSource.class);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("queryTDbSource {} from db",sourceId);
		}
		TDbSourcePage tDbSource=new TDbSourcePage();
		tDbSource.setSourceId(sourceId);
		SqlSession session = dbContext.getReadSession(dataSource);
		TDbSource retVal = session.selectOne("TDBSOURCE.queryTDbSource", tDbSource);
		if(retVal != null){
			dbContext.publishReadPojo(dataSource, retVal,String.valueOf(retVal.getSourceId()));
		}
		return retVal;
    }
	
	/** 查询 **/
	protected List<TDbSource> listTDbSource(TDbSourcePage tDbSource){
	    tDbSource.setPageSize(tDbSource.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TDbSource > list = session.selectList("TDBSOURCE.listTDbSource", tDbSource);
		dbContext.publishReadList(dataSource, TDbSource.class,tDbSource, list);
		tDbSource.setPageSize(tDbSource.getPageSize() - 1);
		if(list.size() > tDbSource.getPageSize()){
		   tDbSource.setNextPage(true);
		   list.remove((int)tDbSource.getPageSize());
		}
		else{
		   tDbSource.setNextPage(false);
		}
		return list;
	}
	
	
	/** 查询数量 **/
	protected long countTDbSource(TDbSourcePage tDbSource){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("TDBSOURCE.countTDbSource",  tDbSource);
		dbContext.publishReadCount(dataSource, TDbSource.class, tDbSource,count);
		return count;
	}
	
	
	/** 分页查询 **/
	public PagedResult<TDbSource> pagedResultOfListTDbSource(
			TDbSourcePage tDbSource) {
		PagedResult<TDbSource> result = new PagedResult<>();
		result.setList(listTDbSource(tDbSource));
		result.setHasNextPage(tDbSource.isNextPage());
		result.setCount(countTDbSource(tDbSource));
		return result;
	}
	
	
	public List<TDbSource> listTDbSourceByIdsFromDB(List<Long> sourceIds){
		if(CollectionUtils.isEmpty(sourceIds)){
			return new ArrayList<TDbSource>();
		}
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TDbSource> list = session.selectList("TDBSOURCE.listTDbSourceByIds",  sourceIds);
		dbContext.publishReadList(dataSource, TDbSource.class,sourceIds, list);
		return list;
	}

	public List<TDbSource> listTDbSourceByIds(List<Long> sourceIds){
		if (CollectionUtils.isEmpty(sourceIds)) {
			return new ArrayList<TDbSource>();
		}
		DBVariable invocationContext = DBVariable.getDBVariable();
		List<Long> hitList = new ArrayList<>();
		List<TDbSource> localCacheList = new ArrayList<>();
		for (Long sourceId : sourceIds) {
			TDbSource localCache = (TDbSource) invocationContext.getLocalCachePojo(TDbSource.class,
					String.valueOf(sourceId));
			if (null != localCache) {
				hitList.add(sourceId);
				localCacheList.add(localCache);
			}
		}
		sourceIds.removeAll(hitList);
		// 如果不走缓存，就直接从数据库中取
		if (!this.isCachable()) {
			List<TDbSource> dbList = listTDbSourceByIdsFromDB(sourceIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<byte[]> values = repository.getMultiByteValue(ListUtils.list2ByteList(sourceIds));
		// redis访问失败，就从数据库中读
		if (null == values) {
			List<TDbSource> dbList = listTDbSourceByIdsFromDB(sourceIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<TDbSource> retList = new ArrayList<TDbSource>(sourceIds.size());
		List<Long> notCachedidList = new ArrayList<>(sourceIds.size());
		// 缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for (int i = 0; i < values.size(); i++) {
			byte[] value = values.get(i);
			if (null == value) {
				notCachedidList.add(sourceIds.get(i));
			} else {
				retList.add(SerializerUtil.deserialize(value, TDbSource.class));
			}
		}
		// 全部都在缓存中
		if (notCachedidList.isEmpty()) {
			retList.addAll(localCacheList);
			return retList;
		}

		// 从数据库中获取缓存中没有的对象
		List<TDbSource> objs = this.listTDbSourceByIdsFromDB(notCachedidList);
		// 根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		retList.addAll(localCacheList);
		return retList;
	}
	
	/*
	 * 加载指定条件的所有id
	 * @param TDbSourcePage tDbSource
	 */
	public List<Long> listIds(TDbSourcePage tDbSource){
	    tDbSource.setPageSize(tDbSource.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<Long> list = session.selectList("TDBSOURCE.listIds", tDbSource);
		dbContext.publishReadList(dataSource, TDbSource.class,tDbSource, list);
		tDbSource.setPageSize(tDbSource.getPageSize() - 1);
		if(list.size() > tDbSource.getPageSize()){
		   tDbSource.setNextPage(true);
		   list.remove((int)tDbSource.getPageSize());
		}
		else{
		   tDbSource.setNextPage(false);
		}		
		return list;
	}
	
   /**
	* 获取最大主键
	*/		
	public long getMaxSequence(){
		SqlSession session = dbContext.getReadSession(dataSource);
		long maxSequence = session.selectOne("TDBSOURCE.selectMaxTDbSourceSequence");
		return maxSequence;	  
	}
}
