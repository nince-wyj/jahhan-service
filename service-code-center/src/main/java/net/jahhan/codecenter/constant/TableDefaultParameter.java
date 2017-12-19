package net.jahhan.codecenter.constant;

import net.jahhan.codecenter.bean.ParaEntity;

public class TableDefaultParameter {
	public static final ParaEntity LOCALCACHE = new ParaEntity("localCache", "true");

	public static final ParaEntity REDISCACHE = new ParaEntity("redisCache", "true");

	public static final ParaEntity METHODEXTENTION = new ParaEntity("methodExtention", "false");
}
