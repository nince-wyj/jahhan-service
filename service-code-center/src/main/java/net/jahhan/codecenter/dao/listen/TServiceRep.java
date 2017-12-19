package net.jahhan.codecenter.dao.listen;

import java.util.EventObject;
import javax.inject.Singleton;
import net.jahhan.codecenter.pojo.TService;

import net.jahhan.cache.constants.RedisConstants;
import net.jahhan.cache.repository.common.AbstractSmpTTLCountRepository;
import net.jahhan.common.extension.constant.BaseConfiguration;
import net.jahhan.jdbc.publish.EventPublisherManager;
import net.jahhan.jdbc.utils.TagUtil;
import net.jahhan.spi.DBEventListener;

/**
 * t_service:服务表
 * 开发人员在此新增缓存操作
 * @author code-generate-service
 */
 @Singleton
public class TServiceRep extends AbstractSmpTTLCountRepository implements DBEventListener{

	private final static String PRE = BaseConfiguration.SERVICE + "_TServiceRep_";

	@Override
	protected String getKey(String id) {
		return PRE + id;
	}

	@Override
	protected String getType() {
		return RedisConstants.TABLE_CACHE;
	}
	
	public TServiceRep() {
		EventPublisherManager.addListener(this);
	}
	
	@Override
	public void listen(EventObject event) {
		onListen(event,TService.class);
	}

	@Override
	public String[] getTags() {
		return TagUtil.getTags(TService.class);
	}
}
