package ${serviceInfo.codePara["abstrImpl_package_path"]};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import ${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className};
import ${serviceInfo.codePara["dao_package_path"]}.${tableInfo.className}Dao;
import ${serviceInfo.codePara["page_package_path"]}.${tableInfo.className}Page;
import ${serviceInfo.codePara["rep_package_path"]}.${tableInfo.className}Rep;

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
 @Slf4j(topic = "dao.${tableInfo.className}Dao")
public abstract class Abstr${tableInfo.className}Impl implements ${tableInfo.className}Dao {

	protected String dataSource = "${tableInfo.dbSource}";
	@Inject
	private ${tableInfo.className}Rep repository;
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
	protected String pkToString(${tableInfo.className} ${tableInfo.className?uncap_first}){
	<#if tableInfo.getSingleKey().getJavaType()='String'>
		return ${tableInfo.className?uncap_first}.get${tableInfo.getSingleKey().javaName?cap_first}();
	<#else>	
		return ((Number)${tableInfo.className?uncap_first}.get${tableInfo.getSingleKey().javaName?cap_first}()).toString();
	</#if>
	}
	
	/** 增加  **/
	public int add${tableInfo.className}(${tableInfo.className} ${tableInfo.className?uncap_first}){
	<#if tableInfo.isAutoInc() >
		<#if tableInfo.getSingleKey().getJavaType()='Long' || tableInfo.getSingleKey().getSamllJavaType()='long'>
		${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(SeqRepository.inc("seq_${tableInfo.dbTableName}"));
		<#elseif tableInfo.getSingleKey().getJavaType()='Integer' || tableInfo.getSingleKey().getSamllJavaType()='int'> 
		${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}((int)SeqRepository.inc("seq_${tableInfo.dbTableName}"));
		<#elseif tableInfo.getSingleKey().getJavaType()='String'>
		</#if>
	</#if>
		${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(${tableInfo.className?uncap_first}, Create.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.insert("${tableInfo.className?upper_case}.add${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.INSERT, pkToString(${tableInfo.className?uncap_first}));
		}
		return ret;
	}
	
	/** 批量增加 **/
	public int addBatch${tableInfo.className}(List<${tableInfo.className}> ${tableInfo.className?uncap_first}List) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (${tableInfo.className} ${tableInfo.className?uncap_first} : ${tableInfo.className?uncap_first}List) {
		<#if tableInfo.isAutoInc() >
			<#if tableInfo.getSingleKey().getJavaType()='Long' || tableInfo.getSingleKey().getSamllJavaType()='long'>
			${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(SeqRepository.inc("seq_${tableInfo.dbTableName}"));
			<#elseif tableInfo.getSingleKey().getJavaType()='Integer' || tableInfo.getSingleKey().getSamllJavaType()='int'> 
			${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}((int)SeqRepository.inc("seq_${tableInfo.dbTableName}"));
			<#elseif tableInfo.getSingleKey().getJavaType()='String'>
			</#if>
		<#else>
			${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(null);
		</#if>
			${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
			addBatch${tableInfo.className}(session, ${tableInfo.className?uncap_first});
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void addBatch${tableInfo.className}(SqlSession session, ${tableInfo.className} ${tableInfo.className?uncap_first}) {
		ValidationUtil.validate(${tableInfo.className?uncap_first}, Create.class);
		int ret = session.insert("${tableInfo.className?upper_case}.add${tableInfo.className}", ${tableInfo.className?uncap_first});
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.INSERT,
					pkToString(${tableInfo.className?uncap_first}));
		}
	}
	
	/** 删除 **/
	public int del${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName}){
		${tableInfo.className} ${tableInfo.className?uncap_first}=new ${tableInfo.className}();
		${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(${tableInfo.getSingleKey().javaName});
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.delete("${tableInfo.className?upper_case}.del${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(ret > 0){
			dbContext.publishDeleteEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, pkToString(${tableInfo.className?uncap_first}));
		}
		return ret;
		
	}
	
	/** 根据主键列表批量删除**/
	public int delByIds(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s){
		if(null!=${tableInfo.getSingleKey().javaName}s&&${tableInfo.getSingleKey().javaName}s.size()>0){
			SqlSession session = dbContext.getWriteSession(dataSource);
			int ret = session.delete("${tableInfo.className?upper_case}.del${tableInfo.className}ByIds", ${tableInfo.getSingleKey().javaName}s);
			if(ret > 0){
				for(${tableInfo.getSingleKey().javaType} ${tableInfo.getSingleKey().javaName}:${tableInfo.getSingleKey().javaName}s){
					${tableInfo.className} ${tableInfo.className?uncap_first}=new ${tableInfo.className}();
					${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(${tableInfo.getSingleKey().javaName});
					dbContext.publishDeleteEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, pkToString(${tableInfo.className?uncap_first}));
				}
			}
			return ret;
		}
		log.debug("${tableInfo.getSingleKey().javaName}s is empty!");
		return 0;
	}
	
   /**
	* 根据对象主键更新对象所有字段
	* 注意：属性为Null也会更新置空 
	*/
	public int resetAll${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName},${tableInfo.className} ${tableInfo.className?uncap_first}){
	    ${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(${tableInfo.getSingleKey().javaName});
		${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("${tableInfo.className?upper_case}.update${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.UPDATE, pkToString(${tableInfo.className?uncap_first}));
		}
		return ret;
	}
	
   /**
	* 全更新，注意为Null的属生也会更新 
	*/
	protected int resetAll${tableInfo.className}(${tableInfo.className} ${tableInfo.className?uncap_first}){
		${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("${tableInfo.className?upper_case}.update${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.UPDATE, pkToString(${tableInfo.className?uncap_first}));
		}
		return ret;
	}
	
	/** 批量全更新 **/
	protected int resetBatch${tableInfo.className}(List<${tableInfo.className}> ${tableInfo.className?uncap_first}List) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (${tableInfo.className} ${tableInfo.className?uncap_first} : ${tableInfo.className?uncap_first}List) {
			${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
			resetBatch${tableInfo.className}(session, ${tableInfo.className?uncap_first});
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void resetBatch${tableInfo.className}(SqlSession session, ${tableInfo.className} ${tableInfo.className?uncap_first}) {
		int ret = session.update("${tableInfo.className?upper_case}.update${tableInfo.className}", ${tableInfo.className?uncap_first});
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.UPDATE, pkToString(${tableInfo.className?uncap_first}));
		}
	}
	
   /**
	* 根据对象主键部分更新字段
	* 注意：属性为Null不会更新
	*/		
	public int updatePart${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName},${tableInfo.className} ${tableInfo.className?uncap_first}){
	    ${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(${tableInfo.getSingleKey().javaName});
		${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(${tableInfo.className?uncap_first}, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("${tableInfo.className?upper_case}.updatePart${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.PART_MODIFY, pkToString(${tableInfo.className?uncap_first}));
		}
		return ret;
	}
	
   /**
	* 部分更新,属性不为Null的会更新
	*/	
	protected int updatePart${tableInfo.className}(${tableInfo.className} ${tableInfo.className?uncap_first}){
		${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(${tableInfo.className?uncap_first}, Modify.class);
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("${tableInfo.className?upper_case}.updatePart${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(ret > 0){
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.PART_MODIFY, pkToString(${tableInfo.className?uncap_first}));
		}
		return ret;
	}

	/** 批量更新 **/
	protected int updatePartBatch${tableInfo.className}(List<${tableInfo.className}> ${tableInfo.className?uncap_first}List) {
		SqlSession session = dbContext.getBatchSession(dataSource);
		int i = 0;
		for (${tableInfo.className} ${tableInfo.className?uncap_first} : ${tableInfo.className?uncap_first}List) {
			${tableInfo.className?uncap_first}.setModifyTimestamp(System.currentTimeMillis());
			ValidationUtil.validate(${tableInfo.className?uncap_first}, Modify.class);
			updatePartBatch${tableInfo.className}(dataSource, session, ${tableInfo.className?uncap_first});
			i++;
		}
		session.flushStatements();
		return i;
	}

	private void updatePartBatch${tableInfo.className}(String dataSource, SqlSession session, ${tableInfo.className} ${tableInfo.className?uncap_first}) {
		int ret = session.update("${tableInfo.className?upper_case}.updatePart${tableInfo.className}", ${tableInfo.className?uncap_first});
		if (ret > 0) {
			dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.PART_MODIFY, pkToString(${tableInfo.className?uncap_first}));
		}
	}
	
	/**
	 * 根据主键按条件部分更新
	 * @param List<${tableInfo.getSingleKey().javaType}>  ${tableInfo.getSingleKey().javaName}s 主键列表
	 * @param ${tableInfo.className} condition${tableInfo.className} 旧值(条件)
	 * @param ${tableInfo.className} new${tableInfo.className} 新值
	 */
	public int updatePartByIds(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s,${tableInfo.className}Page condition${tableInfo.className},${tableInfo.className} new${tableInfo.className}){
		new${tableInfo.className}.setModifyTimestamp(System.currentTimeMillis());
		ValidationUtil.validate(new${tableInfo.className}, Modify.class);
		Map<String,Object> map=new HashMap<>();
		if(CollectionUtils.isEmpty(${tableInfo.getSingleKey().javaName}s)){
			log.debug("${tableInfo.getSingleKey().javaName}s is empty!");
			return 0;
		}
		map.put("ids", ${tableInfo.getSingleKey().javaName}s);
		if(condition${tableInfo.className}!=null){
			condition${tableInfo.className}.set${tableInfo.getSingleKey().javaName?cap_first}(null);
			map.put("oldObj", condition${tableInfo.className});
		}
		map.put("newObj", new${tableInfo.className});
		SqlSession session = dbContext.getWriteSession(dataSource);
		int ret = session.update("${tableInfo.className?upper_case}.updatePart${tableInfo.className}ByIds", map);
		if(ret > 0){
			${tableInfo.className} ${tableInfo.className?uncap_first}=new${tableInfo.className};
			for(${tableInfo.getSingleKey().javaType} ${tableInfo.getSingleKey().javaName}:${tableInfo.getSingleKey().javaName}s){
				${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(${tableInfo.getSingleKey().javaName});
				dbContext.publishDataModifyEvent(dataSource, session.getConnection(), ${tableInfo.className?uncap_first}, EventOperate.OTHER_MODIFY, pkToString(${tableInfo.className?uncap_first}));
			}
		}
		return ret;
	}
	
	/** 加载 **/
	public ${tableInfo.className} query${tableInfo.className}(${tableInfo.getSingleKey().getSamllJavaType()} ${tableInfo.getSingleKey().javaName}){
	<#if tableInfo.tablePara["localCache"] == "true" >
		DBVariable invocationContext = DBVariable.getDBVariable();
		${tableInfo.className} localCache = (${tableInfo.className}) invocationContext.getLocalCachePojo(${tableInfo.className}.class,
				String.valueOf(${tableInfo.getSingleKey().javaName}));
		if (null != localCache) {
			return localCache;
		}
		if (isCachable() && !invocationContext.isDeletePojo(${tableInfo.className}.class, String.valueOf(${tableInfo.getSingleKey().javaName}))) {
			byte[] bytes = repository.getBytes(String.valueOf(${tableInfo.getSingleKey().javaName}));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, ${tableInfo.className}.class);
			}
		}
	<#else>
		if(isCachable()){
			byte[] bytes = repository.getBytes(String.valueOf(${tableInfo.getSingleKey().javaName}));
			if(bytes != null){
				return SerializerUtil.deserialize(bytes, ${tableInfo.className}.class);
			}
		}
	</#if>
		if(log.isDebugEnabled()){
			log.debug("query${tableInfo.className} {} from db",${tableInfo.getSingleKey().javaName});
		}
		${tableInfo.className}Page ${tableInfo.className?uncap_first}=new ${tableInfo.className}Page();
		${tableInfo.className?uncap_first}.set${tableInfo.getSingleKey().javaName?cap_first}(${tableInfo.getSingleKey().javaName});
		SqlSession session = dbContext.getReadSession(dataSource);
		${tableInfo.className} retVal = session.selectOne("${tableInfo.className?upper_case}.query${tableInfo.className}", ${tableInfo.className?uncap_first});
		if(retVal != null){
			dbContext.publishReadPojo(dataSource, retVal,String.valueOf(retVal.get${tableInfo.getSingleKey().javaName?cap_first}()));
		}
		return retVal;
    }
	
	/** 查询 **/
	protected List<${tableInfo.className}> list${tableInfo.className}(${tableInfo.className}Page ${tableInfo.className?uncap_first}){
	    ${tableInfo.className?uncap_first}.setPageSize(${tableInfo.className?uncap_first}.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<${tableInfo.className} > list = session.selectList("${tableInfo.className?upper_case}.list${tableInfo.className}", ${tableInfo.className?uncap_first});
		dbContext.publishReadList(dataSource, ${tableInfo.className}.class,${tableInfo.className?uncap_first}, list);
		${tableInfo.className?uncap_first}.setPageSize(${tableInfo.className?uncap_first}.getPageSize() - 1);
		if(list.size() > ${tableInfo.className?uncap_first}.getPageSize()){
		   ${tableInfo.className?uncap_first}.setNextPage(true);
		   list.remove((int)${tableInfo.className?uncap_first}.getPageSize());
		}
		else{
		   ${tableInfo.className?uncap_first}.setNextPage(false);
		}
		return list;
	}
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	/** 模糊查询 **/
	protected List<${tableInfo.className}> likeList${tableInfo.className}(${tableInfo.className}Page ${tableInfo.className?uncap_first}){
	    ${tableInfo.className?uncap_first}.setPageSize(${tableInfo.className?uncap_first}.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<${tableInfo.className} > list = session.selectList("${tableInfo.className?upper_case}.likeList${tableInfo.className}", ${tableInfo.className?uncap_first});
		dbContext.publishReadList(dataSource, ${tableInfo.className}.class,${tableInfo.className?uncap_first}, list);
		${tableInfo.className?uncap_first}.setPageSize(${tableInfo.className?uncap_first}.getPageSize() - 1);
		if(list.size() > ${tableInfo.className?uncap_first}.getPageSize()){
		   ${tableInfo.className?uncap_first}.setNextPage(true);
		   list.remove((int)${tableInfo.className?uncap_first}.getPageSize());
		}
		else{
		   ${tableInfo.className?uncap_first}.setNextPage(false);
		}
		return list;
	}
	</#if>
	
	/** 查询数量 **/
	protected long count${tableInfo.className}(${tableInfo.className}Page ${tableInfo.className?uncap_first}){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("${tableInfo.className?upper_case}.count${tableInfo.className}",  ${tableInfo.className?uncap_first});
		dbContext.publishReadCount(dataSource, ${tableInfo.className}.class, ${tableInfo.className?uncap_first},count);
		return count;
	}
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	/** 查询数量 **/
	protected long countLike${tableInfo.className}(${tableInfo.className}Page ${tableInfo.className?uncap_first}){
		SqlSession session = dbContext.getReadSession(dataSource);
		long count = session.selectOne("${tableInfo.className?upper_case}.countLike${tableInfo.className}",  ${tableInfo.className?uncap_first});
		dbContext.publishReadCount(dataSource, ${tableInfo.className}.class, ${tableInfo.className?uncap_first},count);
		return count;
	}
	</#if>
	
	/** 分页查询 **/
	public PagedResult<${tableInfo.className}> pagedResultOfList${tableInfo.className}(
			${tableInfo.className}Page ${tableInfo.className?uncap_first}) {
		PagedResult<${tableInfo.className}> result = new PagedResult<>();
		result.setList(list${tableInfo.className}(${tableInfo.className?uncap_first}));
		result.setHasNextPage(${tableInfo.className?uncap_first}.isNextPage());
		result.setCount(count${tableInfo.className}(${tableInfo.className?uncap_first}));
		return result;
	}
	
	<#if tableInfo.tablePara["methodExtention"] == "true" >
	/** 分页查询 **/
	public PagedResult<${tableInfo.className}> pagedResultOfLike${tableInfo.className}(
			${tableInfo.className}Page ${tableInfo.className?uncap_first}) {
		PagedResult<${tableInfo.className}> result = new PagedResult<>();
		result.setList(likeList${tableInfo.className}(${tableInfo.className?uncap_first}));
		result.setHasNextPage(${tableInfo.className?uncap_first}.isNextPage());
		result.setCount(countLike${tableInfo.className}(${tableInfo.className?uncap_first}));
		return result;
	}
	</#if>
	
	public List<${tableInfo.className}> list${tableInfo.className}ByIdsFromDB(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s){
		if(CollectionUtils.isEmpty(${tableInfo.getSingleKey().javaName}s)){
			return new ArrayList<${tableInfo.className}>();
		}
		SqlSession session = dbContext.getReadSession(dataSource);
		List<${tableInfo.className}> list = session.selectList("${tableInfo.className?upper_case}.list${tableInfo.className}ByIds",  ${tableInfo.getSingleKey().javaName}s);
		dbContext.publishReadList(dataSource, ${tableInfo.className}.class,${tableInfo.getSingleKey().javaName}s, list);
		return list;
	}

	public List<${tableInfo.className}> list${tableInfo.className}ByIds(List<${tableInfo.getSingleKey().javaType}> ${tableInfo.getSingleKey().javaName}s){
	<#if tableInfo.tablePara["localCache"] == "true" >
		if (CollectionUtils.isEmpty(${tableInfo.getSingleKey().javaName}s)) {
			return new ArrayList<${tableInfo.className}>();
		}
		DBVariable invocationContext = DBVariable.getDBVariable();
		List<${tableInfo.getSingleKey().javaType}> hitList = new ArrayList<>();
		List<${tableInfo.className}> localCacheList = new ArrayList<>();
		for (${tableInfo.getSingleKey().javaType} ${tableInfo.getSingleKey().javaName} : ${tableInfo.getSingleKey().javaName}s) {
			${tableInfo.className} localCache = (${tableInfo.className}) invocationContext.getLocalCachePojo(${tableInfo.className}.class,
					String.valueOf(${tableInfo.getSingleKey().javaName}));
			if (null != localCache) {
				hitList.add(${tableInfo.getSingleKey().javaName});
				localCacheList.add(localCache);
			}
		}
		${tableInfo.getSingleKey().javaName}s.removeAll(hitList);
		// 如果不走缓存，就直接从数据库中取
		if (!this.isCachable()) {
			List<${tableInfo.className}> dbList = list${tableInfo.className}ByIdsFromDB(${tableInfo.getSingleKey().javaName}s);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<byte[]> values = repository.getMultiByteValue(ListUtils.list2ByteList(${tableInfo.getSingleKey().javaName}s));
		// redis访问失败，就从数据库中读
		if (null == values) {
			List<${tableInfo.className}> dbList = list${tableInfo.className}ByIdsFromDB(${tableInfo.getSingleKey().javaName}s);
			dbList.addAll(localCacheList);
			return dbList;
		}
		List<${tableInfo.className}> retList = new ArrayList<${tableInfo.className}>(${tableInfo.getSingleKey().javaName}s.size());
		List<${tableInfo.getSingleKey().javaType}> notCachedidList = new ArrayList<>(${tableInfo.getSingleKey().javaName}s.size());
		// 缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for (int i = 0; i < values.size(); i++) {
			byte[] value = values.get(i);
			if (null == value) {
				notCachedidList.add(${tableInfo.getSingleKey().javaName}s.get(i));
			} else {
				retList.add(SerializerUtil.deserialize(value, ${tableInfo.className}.class));
			}
		}
		// 全部都在缓存中
		if (notCachedidList.isEmpty()) {
			retList.addAll(localCacheList);
			return retList;
		}

		// 从数据库中获取缓存中没有的对象
		List<${tableInfo.className}> objs = this.list${tableInfo.className}ByIdsFromDB(notCachedidList);
		// 根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		retList.addAll(localCacheList);
		return retList;
	<#else>
		//如果不走缓存，就直接从数据库中取
		if(!this.isCachable()){
			return this.list${tableInfo.className}ByIdsFromDB(${tableInfo.getSingleKey().javaName}s);
		}
		if(CollectionUtils.isEmpty(${tableInfo.getSingleKey().javaName}s)){
			return new ArrayList<${tableInfo.className}>();
		}
		List<byte[]> values=repository.getMultiByteValue(ListUtils.list2ByteList(${tableInfo.getSingleKey().javaName}s));
		//redis访问失败，就从数据库中读
		if(null == values){
			return this.list${tableInfo.className}ByIdsFromDB(${tableInfo.getSingleKey().javaName}s);
		}
		List<${tableInfo.className}> retList=new ArrayList<${tableInfo.className}>(${tableInfo.getSingleKey().javaName}s.size());
		List<${tableInfo.getSingleKey().javaType}> notCachedidList=new ArrayList<>(${tableInfo.getSingleKey().javaName}s.size());
		//缓存中有的对象，就直接从缓存中获取，不存在的对象，就将id保存起来。
		for(int i=0;i<values.size();i++){
			byte[] value=values.get(i);
			if(null == value){
				notCachedidList.add(${tableInfo.getSingleKey().javaName}s.get(i));
			}else{
				retList.add(SerializerUtil.deserialize(value, ${tableInfo.className}.class));
			}
		}
		//全部都在缓存中
		if(notCachedidList.isEmpty()){
			return retList;
		}
		
		//从数据库中获取缓存中没有的对象
		List<${tableInfo.className}> objs=this.list${tableInfo.className}ByIdsFromDB(notCachedidList);
		//根据主键查询不可能存在重复的对象，所以不需要去重
		retList.addAll(objs);
		return retList;
	</#if>
	}
	
	/*
	 * 加载指定条件的所有id
	 * @param ${tableInfo.className}Page ${tableInfo.className?uncap_first}
	 */
	public List<${tableInfo.getSingleKey().javaType}> listIds(${tableInfo.className}Page ${tableInfo.className?uncap_first}){
	    ${tableInfo.className?uncap_first}.setPageSize(${tableInfo.className?uncap_first}.getPageSize() + 1);
		SqlSession session = dbContext.getReadSession(dataSource);
		List<${tableInfo.getSingleKey().javaType}> list = session.selectList("${tableInfo.className?upper_case}.listIds", ${tableInfo.className?uncap_first});
		dbContext.publishReadList(dataSource, ${tableInfo.className}.class,${tableInfo.className?uncap_first}, list);
		${tableInfo.className?uncap_first}.setPageSize(${tableInfo.className?uncap_first}.getPageSize() - 1);
		if(list.size() > ${tableInfo.className?uncap_first}.getPageSize()){
		   ${tableInfo.className?uncap_first}.setNextPage(true);
		   list.remove((int)${tableInfo.className?uncap_first}.getPageSize());
		}
		else{
		   ${tableInfo.className?uncap_first}.setNextPage(false);
		}		
		return list;
	}
	
   /**
	* 获取最大主键
	*/		
	public ${tableInfo.getSingleKey().getSamllJavaType()} getMaxSequence(){
		SqlSession session = dbContext.getReadSession(dataSource);
		${tableInfo.getSingleKey().getSamllJavaType()} maxSequence = session.selectOne("${tableInfo.className?upper_case}.selectMax${tableInfo.className}Sequence");
		return maxSequence;	  
	}
}
