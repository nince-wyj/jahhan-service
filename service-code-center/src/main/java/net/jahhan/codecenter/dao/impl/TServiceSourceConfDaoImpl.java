package net.jahhan.codecenter.dao.impl;

import net.jahhan.codecenter.dao.abstrimpl.AbstrTServiceSourceConfImpl;
import net.jahhan.codecenter.pojo.TServiceSourceConf;

/*
 * 开发人员在此实现接口方法
 *
 * @author code-generate-service
 */
public class TServiceSourceConfDaoImpl  extends AbstrTServiceSourceConfImpl {
	
	@Override
	protected boolean isCachable() {
		return false;//如果需要使用缓存，请使用TServiceSourceConfDaoCache
	}
}