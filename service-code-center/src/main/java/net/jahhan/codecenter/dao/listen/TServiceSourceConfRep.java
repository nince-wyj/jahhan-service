package net.jahhan.codecenter.dao.listen;

import java.util.EventObject;
import javax.inject.Singleton;
import net.jahhan.codecenter.pojo.TServiceSourceConf;

import net.jahhan.cache.constants.RedisConstants;
import net.jahhan.cache.repository.common.AbstractSmpTTLCountRepository;
import net.jahhan.common.extension.constant.BaseConfiguration;
import net.jahhan.jdbc.publish.EventPublisherManager;
import net.jahhan.jdbc.utils.TagUtil;
import net.jahhan.spi.DBEventListener;

/**
 * t_service_source_conf:服务数据源配置表
 * 开发人员在此新增缓存操作
 * @author code-generate-service
 */
 @Singleton
public class TServiceSourceConfRep extends AbstractSmpTTLCountRepository implements DBEventListener{

	private final static String PRE = BaseConfiguration.SERVICE + "_TServiceSourceConfRep_";

	@Override
	protected String getKey(String id) {
		return PRE + id;
	}

	@Override
	protected String getType() {
		return RedisConstants.TABLE_CACHE;
	}
	
	public TServiceSourceConfRep() {
		EventPublisherManager.addListener(this);
	}
	
	@Override
	public void listen(EventObject event) {
		onListen(event,TServiceSourceConf.class);
	}

	@Override
	public String[] getTags() {
		return TagUtil.getTags(TServiceSourceConf.class);
	}
}
