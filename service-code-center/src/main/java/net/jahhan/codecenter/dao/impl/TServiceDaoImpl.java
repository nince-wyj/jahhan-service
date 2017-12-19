package net.jahhan.codecenter.dao.impl;

import net.jahhan.codecenter.dao.abstrimpl.AbstrTServiceImpl;
import net.jahhan.codecenter.pojo.TService;

/*
 * 开发人员在此实现接口方法
 *
 * @author code-generate-service
 */
public class TServiceDaoImpl  extends AbstrTServiceImpl {
	
	@Override
	protected boolean isCachable() {
		return false;//如果需要使用缓存，请使用TServiceDaoCache
	}
}