package net.jahhan.codecenter.dao.abstrimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import net.jahhan.codecenter.pojo.TDb;
import net.jahhan.codecenter.dao.TDbDao;
import net.jahhan.codecenter.pojo.page.TDbPage;
import net.jahhan.codecenter.dao.listen.TDbRep;

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
 @Slf4j(topic = "dao.TDbDao")
public abstract class AbstrTDbImpl implements TDbDao {

	protected String dataSource = "code_center";
	@Inject
	private TDbRep repository;
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
	protected String pkToString(TDb tDb){
		return ((Number)tDb.getDbId()).toString();
	}
	
	/** 增加  **/
	public int addTDb(TDb tDb){
		tDb.setDbId(SeqRepository.inc("seq_t_db"));
		tDb.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDb, Create.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.insert("TDB.addTDb", tDb);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.INSERT, pkToString(tDb));
		}
		return ret;
	}
	
	/** 批量增加 **/
	public int addBatchTDb(List<TDb> tDbList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDb tDb : tDbList) {
			tDb.setDbId(SeqRepository.inc("seq_t_db"));
			tDb.setModifyTimestamp(System.currentTimeMillis());
			addBatchTDb(session, tDb);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void addBatchTDb(SqlSession session, TDb tDb) {
		ValidationUtil.validate(tDb, Create.class);
		int ret = session.insert("TDB.addTDb", tDb);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.INSERT,
					pkToString(tDb));
		}
	}
	
	/** 删除 **/
	public int delTDb(long dbId){
		TDb tDb=new TDb();
		tDb.setDbId(dbId);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.delete("TDB.delTDb", tDb);
		if(ret > 0){
			dbContext.publishDeleteEvent(dataSource, session.getConnection(), tDb, pkToString(tDb));
		}
		return ret;
		
	}
	
	/** 根据主键列表批量删除**/
	public int delByIds(List<Long> dbIds){
		if(null!=dbIds&&dbIds.size()>0){
			SqlSession session = dbContext.getWriteSession(dataSource);
			int ret = session.delete("TDB.delTDbByIds", dbIds);
			if(ret > 0){
				for(Long dbId:dbIds){
					TDb tDb=new TDb();
					tDb.setDbId(dbId);
					dbContext.publishDeleteEvent(dataSource, session.getConnection(), tDb, pkToString(tDb));
				}
			}
			return ret;
		}
		log.debug("dbIds is empty!");
		return 0;
	}
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	public int resetAllTDb(long dbId,TDb tDb){
	    tDb.setDbId(dbId);
		tDb.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDB.updateTDb", tDb);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.UPDATE, pkToString(tDb));
		}
		return ret;
	}
	
   /**
	* 全更新，注意为Null的属生也会更新 
	*/
	protected int resetAllTDb(TDb tDb){
		tDb.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDB.updateTDb", tDb);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.UPDATE, pkToString(tDb));
		}
		return ret;
	}
	
	/** 批量全更新 **/
	protected int resetBatchTDb(List<TDb> tDbList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDb tDb : tDbList) {
			tDb.setModifyTimestamp(System.currentTimeMillis());
			resetBatchTDb(session, tDb);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void resetBatchTDb(SqlSession session, TDb tDb) {
		int ret = session.update("TDB.updateTDb", tDb);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.UPDATE, pkToString(tDb));
		}
	}
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/		
	public int updatePartTDb(long dbId,TDb tDb){
	    tDb.setDbId(dbId);
		tDb.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDb, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDB.updatePartTDb", tDb);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.PART_MODIFY, pkToString(tDb));
		}
		return ret;
	}
	
   /**
	* 部分更新,属性不为Null的会更新
	*/	
	protected int updatePartTDb(TDb tDb){
		tDb.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDb, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDB.updatePartTDb", tDb);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.PART_MODIFY, pkToString(tDb));
		}
		return ret;
	}

	/** 批量更新 **/
	protected int updatePartBatchTDb(List<TDb> tDbList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDb tDb : tDbList) {
			tDb.setModifyTimestamp(System.currentTimeMillis());
			ValidationUtil.validate(tDb, Modify.class);
			updatePartBatchTDb(dataSource, session, tDb);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void updatePartBatchTDb(String dataSource, SqlSession session, TDb tDb) {
		int ret = session.update("TDB.updatePartTDb", tDb);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.PART_MODIFY, pkToString(tDb));
		}
	}
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  dbIds 主键列表
	 * @param TDb conditionTDb 旧值(条件)
	 * @param TDb newTDb 新值
	 */
	public int updatePartByIds(List<Long> dbIds,TDbPage conditionTDb,TDb newTDb){
		newTDb.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(newTDb, Modify.class);
		Map<String,Object> map=new HashMap<>();
		if(CollectionUtils.isEmpty(dbIds)){
			log.debug("dbIds is empty!");
			return 0;
		}
		map.put("ids", dbIds);
		if(conditionTDb!=null){
			conditionTDb.setDbId(null);
			map.put("oldObj", conditionTDb);
		}
		map.put("newObj", newTDb);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDB.updatePartTDbByIds", map);
		if(ret > 0){
			TDb tDb=newTDb;
			for(Long dbId:dbIds){
				tDb.setDbId(dbId);
				dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDb, EventOperate.OTHER_MODIFY, pkToString(tDb));
			}
		}
		return ret;
	}
	
	/** 加载 **/
	public TDb queryTDb(long dbId){
		DBVariable invocationContext = DBVariable.getDBVariable();
		TDb localCache = (TDb) invocationContext.getLocalCachePojo(TDb.class,
				String.valueOf(dbId));
		if (null != localCache) {
			return localCache;
		}
		if (isCachable() && !invocationContext.isDeletePojo(TDb.class, String.valueOf(dbId))) {
			byte[] bytes = repository.getBytes(String.valueOf(dbId));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, TDb.class);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("queryTDb {} from db",dbId);
		}
		TDbPage tDb=new TDbPage();
		tDb.setDbId(dbId);
		SqlSession session = dbContext.getReadSession(dataSource);
		TDb retVal = session.selectOne("TDB.queryTDb", tDb);
		if(retVal != null){
			dbContext.publishReadPojo(dataSource, retVal,String.valueOf(retVal.getDbId()));
		}
		return retVal;
    }
	
	/** 查询 **/
	protected List<TDb> listTDb(TDbPage tDb){
	    tDb.setPageSize(tDb.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TDb > list = session.selectList("TDB.listTDb", tDb);
		dbContext.publishReadList(dataSource, TDb.class,tDb, list);
		tDb.setPageSize(tDb.getPageSize() - 1);
		if(list.size() > tDb.getPageSize()){
		   tDb.setNextPage(true);
		   list.remove((int)tDb.getPageSize());
		}
		else{
		   tDb.setNextPage(false);
		}
		return list;
	}
	
	
	/** 查询数量 **/
	protected long countTDb(TDbPage tDb){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("TDB.countTDb",  tDb);
		dbContext.publishReadCount(dataSource, TDb.class, tDb,count);
		return count;
	}
	
	
	/** 分页查询 **/
	public PagedResult<TDb> pagedResultOfListTDb(
			TDbPage tDb) {
		PagedResult<TDb> result = new PagedResult<>();
		result.setList(listTDb(tDb));
		result.setHasNextPage(tDb.isNextPage());
		result.setCount(countTDb(tDb));
		return result;
	}
	
	
	public List<TDb> listTDbByIdsFromDB(List<Long> dbIds){
		if(CollectionUtils.isEmpty(dbIds)){
			return new ArrayList<TDb>();
		}
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TDb> list = session.selectList("TDB.listTDbByIds",  dbIds);
		dbContext.publishReadList(dataSource, TDb.class,dbIds, list);
		return list;
	}

	public List<TDb> listTDbByIds(List<Long> dbIds){
		if (CollectionUtils.isEmpty(dbIds)) {
			return new ArrayList<TDb>();
		}
		DBVariable invocationContext = DBVariable.getDBVariable();
		List<Long> hitList = new ArrayList<>();
		List<TDb> localCacheList = new ArrayList<>();
		for (Long dbId : dbIds) {
			TDb localCache = (TDb) invocationContext.getLocalCachePojo(TDb.class,
					String.valueOf(dbId));
			if (null != localCache) {
				hitList.add(dbId);
				localCacheList.add(localCache);
			}
		}
		dbIds.removeAll(hitList);
		// 如果不走缓存，就直接从数据库中取
		if (!this.isCachable()) {
			List<TDb> dbList = listTDbByIdsFromDB(dbIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<byte[]> values = repository.getMultiByteValue(ListUtils.list2ByteList(dbIds));
		// redis访问失败，就从数据库中读
		if (null == values) {
			List<TDb> dbList = listTDbByIdsFromDB(dbIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<TDb> retList = new ArrayList<TDb>(dbIds.size());
		List<Long> notCachedidList = new ArrayList<>(dbIds.size());
		// 缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for (int i = 0; i < values.size(); i++) {
			byte[] value = values.get(i);
			if (null == value) {
				notCachedidList.add(dbIds.get(i));
			} else {
				retList.add(SerializerUtil.deserialize(value, TDb.class));
			}
		}
		// 全部都在缓存中
		if (notCachedidList.isEmpty()) {
			retList.addAll(localCacheList);
			return retList;
		}

		// 从数据库中获取缓存中没有的对象
		List<TDb> objs = this.listTDbByIdsFromDB(notCachedidList);
		// 根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		retList.addAll(localCacheList);
		return retList;
	}
	
	/*
	 * 加载指定条件的所有id
	 * @param TDbPage tDb
	 */
	public List<Long> listIds(TDbPage tDb){
	    tDb.setPageSize(tDb.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<Long> list = session.selectList("TDB.listIds", tDb);
		dbContext.publishReadList(dataSource, TDb.class,tDb, list);
		tDb.setPageSize(tDb.getPageSize() - 1);
		if(list.size() > tDb.getPageSize()){
		   tDb.setNextPage(true);
		   list.remove((int)tDb.getPageSize());
		}
		else{
		   tDb.setNextPage(false);
		}		
		return list;
	}
	
   /**
	* 获取最大主键
	*/		
	public long getMaxSequence(){
		SqlSession session = dbContext.getReadSession(dataSource);
		long maxSequence = session.selectOne("TDB.selectMaxTDbSequence");
		return maxSequence;	  
	}
}
