package net.jahhan.codecenter.dao.abstrimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import net.jahhan.codecenter.pojo.TDbScriptInfo;
import net.jahhan.codecenter.dao.TDbScriptInfoDao;
import net.jahhan.codecenter.pojo.page.TDbScriptInfoPage;
import net.jahhan.codecenter.dao.listen.TDbScriptInfoRep;

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
 @Slf4j(topic = "dao.TDbScriptInfoDao")
public abstract class AbstrTDbScriptInfoImpl implements TDbScriptInfoDao {

	protected String dataSource = "code_center";
	@Inject
	private TDbScriptInfoRep repository;
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
	protected String pkToString(TDbScriptInfo tDbScriptInfo){
		return ((Number)tDbScriptInfo.getDbScriptInfoId()).toString();
	}
	
	/** 增加  **/
	public int addTDbScriptInfo(TDbScriptInfo tDbScriptInfo){
		tDbScriptInfo.setDbScriptInfoId(SeqRepository.inc("seq_t_db_script_info"));
		tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDbScriptInfo, Create.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.insert("TDBSCRIPTINFO.addTDbScriptInfo", tDbScriptInfo);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.INSERT, pkToString(tDbScriptInfo));
		}
		return ret;
	}
	
	/** 批量增加 **/
	public int addBatchTDbScriptInfo(List<TDbScriptInfo> tDbScriptInfoList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDbScriptInfo tDbScriptInfo : tDbScriptInfoList) {
			tDbScriptInfo.setDbScriptInfoId(SeqRepository.inc("seq_t_db_script_info"));
			tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
			addBatchTDbScriptInfo(session, tDbScriptInfo);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void addBatchTDbScriptInfo(SqlSession session, TDbScriptInfo tDbScriptInfo) {
		ValidationUtil.validate(tDbScriptInfo, Create.class);
		int ret = session.insert("TDBSCRIPTINFO.addTDbScriptInfo", tDbScriptInfo);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.INSERT,
					pkToString(tDbScriptInfo));
		}
	}
	
	/** 删除 **/
	public int delTDbScriptInfo(long dbScriptInfoId){
		TDbScriptInfo tDbScriptInfo=new TDbScriptInfo();
		tDbScriptInfo.setDbScriptInfoId(dbScriptInfoId);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.delete("TDBSCRIPTINFO.delTDbScriptInfo", tDbScriptInfo);
		if(ret > 0){
			dbContext.publishDeleteEvent(dataSource, session.getConnection(), tDbScriptInfo, pkToString(tDbScriptInfo));
		}
		return ret;
		
	}
	
	/** 根据主键列表批量删除**/
	public int delByIds(List<Long> dbScriptInfoIds){
		if(null!=dbScriptInfoIds&&dbScriptInfoIds.size()>0){
			SqlSession session = dbContext.getWriteSession(dataSource);
			int ret = session.delete("TDBSCRIPTINFO.delTDbScriptInfoByIds", dbScriptInfoIds);
			if(ret > 0){
				for(Long dbScriptInfoId:dbScriptInfoIds){
					TDbScriptInfo tDbScriptInfo=new TDbScriptInfo();
					tDbScriptInfo.setDbScriptInfoId(dbScriptInfoId);
					dbContext.publishDeleteEvent(dataSource, session.getConnection(), tDbScriptInfo, pkToString(tDbScriptInfo));
				}
			}
			return ret;
		}
		log.debug("dbScriptInfoIds is empty!");
		return 0;
	}
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	public int resetAllTDbScriptInfo(long dbScriptInfoId,TDbScriptInfo tDbScriptInfo){
	    tDbScriptInfo.setDbScriptInfoId(dbScriptInfoId);
		tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSCRIPTINFO.updateTDbScriptInfo", tDbScriptInfo);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.UPDATE, pkToString(tDbScriptInfo));
		}
		return ret;
	}
	
   /**
	* 全更新，注意为Null的属生也会更新 
	*/
	protected int resetAllTDbScriptInfo(TDbScriptInfo tDbScriptInfo){
		tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSCRIPTINFO.updateTDbScriptInfo", tDbScriptInfo);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.UPDATE, pkToString(tDbScriptInfo));
		}
		return ret;
	}
	
	/** 批量全更新 **/
	protected int resetBatchTDbScriptInfo(List<TDbScriptInfo> tDbScriptInfoList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDbScriptInfo tDbScriptInfo : tDbScriptInfoList) {
			tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
			resetBatchTDbScriptInfo(session, tDbScriptInfo);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void resetBatchTDbScriptInfo(SqlSession session, TDbScriptInfo tDbScriptInfo) {
		int ret = session.update("TDBSCRIPTINFO.updateTDbScriptInfo", tDbScriptInfo);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.UPDATE, pkToString(tDbScriptInfo));
		}
	}
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/		
	public int updatePartTDbScriptInfo(long dbScriptInfoId,TDbScriptInfo tDbScriptInfo){
	    tDbScriptInfo.setDbScriptInfoId(dbScriptInfoId);
		tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDbScriptInfo, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSCRIPTINFO.updatePartTDbScriptInfo", tDbScriptInfo);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.PART_MODIFY, pkToString(tDbScriptInfo));
		}
		return ret;
	}
	
   /**
	* 部分更新,属性不为Null的会更新
	*/	
	protected int updatePartTDbScriptInfo(TDbScriptInfo tDbScriptInfo){
		tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(tDbScriptInfo, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSCRIPTINFO.updatePartTDbScriptInfo", tDbScriptInfo);
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.PART_MODIFY, pkToString(tDbScriptInfo));
		}
		return ret;
	}

	/** 批量更新 **/
	protected int updatePartBatchTDbScriptInfo(List<TDbScriptInfo> tDbScriptInfoList) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (TDbScriptInfo tDbScriptInfo : tDbScriptInfoList) {
			tDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
			ValidationUtil.validate(tDbScriptInfo, Modify.class);
			updatePartBatchTDbScriptInfo(dataSource, session, tDbScriptInfo);
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void updatePartBatchTDbScriptInfo(String dataSource, SqlSession session, TDbScriptInfo tDbScriptInfo) {
		int ret = session.update("TDBSCRIPTINFO.updatePartTDbScriptInfo", tDbScriptInfo);
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.PART_MODIFY, pkToString(tDbScriptInfo));
		}
	}
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<Long>  dbScriptInfoIds 主键列表
	 * @param TDbScriptInfo conditionTDbScriptInfo 旧值(条件)
	 * @param TDbScriptInfo newTDbScriptInfo 新值
	 */
	public int updatePartByIds(List<Long> dbScriptInfoIds,TDbScriptInfoPage conditionTDbScriptInfo,TDbScriptInfo newTDbScriptInfo){
		newTDbScriptInfo.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(newTDbScriptInfo, Modify.class);
		Map<String,Object> map=new HashMap<>();
		if(CollectionUtils.isEmpty(dbScriptInfoIds)){
			log.debug("dbScriptInfoIds is empty!");
			return 0;
		}
		map.put("ids", dbScriptInfoIds);
		if(conditionTDbScriptInfo!=null){
			conditionTDbScriptInfo.setDbScriptInfoId(null);
			map.put("oldObj", conditionTDbScriptInfo);
		}
		map.put("newObj", newTDbScriptInfo);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("TDBSCRIPTINFO.updatePartTDbScriptInfoByIds", map);
		if(ret > 0){
			TDbScriptInfo tDbScriptInfo=newTDbScriptInfo;
			for(Long dbScriptInfoId:dbScriptInfoIds){
				tDbScriptInfo.setDbScriptInfoId(dbScriptInfoId);
				dbContext.publishDataModifyEvent(dataSource, session.getConnection(), tDbScriptInfo, EventOperate.OTHER_MODIFY, pkToString(tDbScriptInfo));
			}
		}
		return ret;
	}
	
	/** 加载 **/
	public TDbScriptInfo queryTDbScriptInfo(long dbScriptInfoId){
		DBVariable invocationContext = DBVariable.getDBVariable();
		TDbScriptInfo localCache = (TDbScriptInfo) invocationContext.getLocalCachePojo(TDbScriptInfo.class,
				String.valueOf(dbScriptInfoId));
		if (null != localCache) {
			return localCache;
		}
		if (isCachable() && !invocationContext.isDeletePojo(TDbScriptInfo.class, String.valueOf(dbScriptInfoId))) {
			byte[] bytes = repository.getBytes(String.valueOf(dbScriptInfoId));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, TDbScriptInfo.class);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("queryTDbScriptInfo {} from db",dbScriptInfoId);
		}
		TDbScriptInfoPage tDbScriptInfo=new TDbScriptInfoPage();
		tDbScriptInfo.setDbScriptInfoId(dbScriptInfoId);
		SqlSession session = dbContext.getReadSession(dataSource);
		TDbScriptInfo retVal = session.selectOne("TDBSCRIPTINFO.queryTDbScriptInfo", tDbScriptInfo);
		if(retVal != null){
			dbContext.publishReadPojo(dataSource, retVal,String.valueOf(retVal.getDbScriptInfoId()));
		}
		return retVal;
    }
	
	/** 查询 **/
	protected List<TDbScriptInfo> listTDbScriptInfo(TDbScriptInfoPage tDbScriptInfo){
	    tDbScriptInfo.setPageSize(tDbScriptInfo.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TDbScriptInfo > list = session.selectList("TDBSCRIPTINFO.listTDbScriptInfo", tDbScriptInfo);
		dbContext.publishReadList(dataSource, TDbScriptInfo.class,tDbScriptInfo, list);
		tDbScriptInfo.setPageSize(tDbScriptInfo.getPageSize() - 1);
		if(list.size() > tDbScriptInfo.getPageSize()){
		   tDbScriptInfo.setNextPage(true);
		   list.remove((int)tDbScriptInfo.getPageSize());
		}
		else{
		   tDbScriptInfo.setNextPage(false);
		}
		return list;
	}
	
	
	/** 查询数量 **/
	protected long countTDbScriptInfo(TDbScriptInfoPage tDbScriptInfo){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("TDBSCRIPTINFO.countTDbScriptInfo",  tDbScriptInfo);
		dbContext.publishReadCount(dataSource, TDbScriptInfo.class, tDbScriptInfo,count);
		return count;
	}
	
	
	/** 分页查询 **/
	public PagedResult<TDbScriptInfo> pagedResultOfListTDbScriptInfo(
			TDbScriptInfoPage tDbScriptInfo) {
		PagedResult<TDbScriptInfo> result = new PagedResult<>();
		result.setList(listTDbScriptInfo(tDbScriptInfo));
		result.setHasNextPage(tDbScriptInfo.isNextPage());
		result.setCount(countTDbScriptInfo(tDbScriptInfo));
		return result;
	}
	
	
	public List<TDbScriptInfo> listTDbScriptInfoByIdsFromDB(List<Long> dbScriptInfoIds){
		if(CollectionUtils.isEmpty(dbScriptInfoIds)){
			return new ArrayList<TDbScriptInfo>();
		}
		SqlSession session = dbContext.getReadSession(dataSource);
		List<TDbScriptInfo> list = session.selectList("TDBSCRIPTINFO.listTDbScriptInfoByIds",  dbScriptInfoIds);
		dbContext.publishReadList(dataSource, TDbScriptInfo.class,dbScriptInfoIds, list);
		return list;
	}

	public List<TDbScriptInfo> listTDbScriptInfoByIds(List<Long> dbScriptInfoIds){
		if (CollectionUtils.isEmpty(dbScriptInfoIds)) {
			return new ArrayList<TDbScriptInfo>();
		}
		DBVariable invocationContext = DBVariable.getDBVariable();
		List<Long> hitList = new ArrayList<>();
		List<TDbScriptInfo> localCacheList = new ArrayList<>();
		for (Long dbScriptInfoId : dbScriptInfoIds) {
			TDbScriptInfo localCache = (TDbScriptInfo) invocationContext.getLocalCachePojo(TDbScriptInfo.class,
					String.valueOf(dbScriptInfoId));
			if (null != localCache) {
				hitList.add(dbScriptInfoId);
				localCacheList.add(localCache);
			}
		}
		dbScriptInfoIds.removeAll(hitList);
		// 如果不走缓存，就直接从数据库中取
		if (!this.isCachable()) {
			List<TDbScriptInfo> dbList = listTDbScriptInfoByIdsFromDB(dbScriptInfoIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<byte[]> values = repository.getMultiByteValue(ListUtils.list2ByteList(dbScriptInfoIds));
		// redis访问失败，就从数据库中读
		if (null == values) {
			List<TDbScriptInfo> dbList = listTDbScriptInfoByIdsFromDB(dbScriptInfoIds);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<TDbScriptInfo> retList = new ArrayList<TDbScriptInfo>(dbScriptInfoIds.size());
		List<Long> notCachedidList = new ArrayList<>(dbScriptInfoIds.size());
		// 缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for (int i = 0; i < values.size(); i++) {
			byte[] value = values.get(i);
			if (null == value) {
				notCachedidList.add(dbScriptInfoIds.get(i));
			} else {
				retList.add(SerializerUtil.deserialize(value, TDbScriptInfo.class));
			}
		}
		// 全部都在缓存中
		if (notCachedidList.isEmpty()) {
			retList.addAll(localCacheList);
			return retList;
		}

		// 从数据库中获取缓存中没有的对象
		List<TDbScriptInfo> objs = this.listTDbScriptInfoByIdsFromDB(notCachedidList);
		// 根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		retList.addAll(localCacheList);
		return retList;
	}
	
	/*
	 * 加载指定条件的所有id
	 * @param TDbScriptInfoPage tDbScriptInfo
	 */
	public List<Long> listIds(TDbScriptInfoPage tDbScriptInfo){
	    tDbScriptInfo.setPageSize(tDbScriptInfo.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<Long> list = session.selectList("TDBSCRIPTINFO.listIds", tDbScriptInfo);
		dbContext.publishReadList(dataSource, TDbScriptInfo.class,tDbScriptInfo, list);
		tDbScriptInfo.setPageSize(tDbScriptInfo.getPageSize() - 1);
		if(list.size() > tDbScriptInfo.getPageSize()){
		   tDbScriptInfo.setNextPage(true);
		   list.remove((int)tDbScriptInfo.getPageSize());
		}
		else{
		   tDbScriptInfo.setNextPage(false);
		}		
		return list;
	}
	
   /**
	* 获取最大主键
	*/		
	public long getMaxSequence(){
		SqlSession session = dbContext.getReadSession(dataSource);
		long maxSequence = session.selectOne("TDBSCRIPTINFO.selectMaxTDbScriptInfoSequence");
		return maxSequence;	  
	}
}
