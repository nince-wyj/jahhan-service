package net.jahhan.codecenter.constant;

import net.jahhan.codecenter.bean.ParaEntity;

public class TableDefaultParameter {
	public static final ParaEntity LOCAL_CACHE = new ParaEntity("localCache", "true");

	public static final ParaEntity REDIS_CACHE = new ParaEntity("redisCache", "true");

	public static final ParaEntity METHOD_EXTENTION = new ParaEntity("methodExtention", "false");
	
	public static final ParaEntity CACHE_EXPIRE_TIME = new ParaEntity("cacheExpireTime", "0");
}
