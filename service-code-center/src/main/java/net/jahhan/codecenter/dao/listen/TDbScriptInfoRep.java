package net.jahhan.codecenter.dao.listen;

import java.util.EventObject;
import javax.inject.Singleton;
import net.jahhan.codecenter.pojo.TDbScriptInfo;

import net.jahhan.cache.constants.RedisConstants;
import net.jahhan.cache.repository.common.AbstractSmpTTLCountRepository;
import net.jahhan.common.extension.constant.BaseConfiguration;
import net.jahhan.jdbc.publish.EventPublisherManager;
import net.jahhan.jdbc.utils.TagUtil;
import net.jahhan.spi.DBEventListener;

/**
 * t_db_script_info:数据源建表信息表
 * 开发人员在此新增缓存操作
 * @author code-generate-service
 */
 @Singleton
public class TDbScriptInfoRep extends AbstractSmpTTLCountRepository implements DBEventListener{

	private final static String PRE = BaseConfiguration.SERVICE + "_TDbScriptInfoRep_";

	@Override
	protected String getKey(String id) {
		return PRE + id;
	}

	@Override
	protected String getType() {
		return RedisConstants.TABLE_CACHE;
	}
	
	public TDbScriptInfoRep() {
		EventPublisherManager.addListener(this);
	}
	
	@Override
	public void listen(EventObject event) {
		onListen(event,TDbScriptInfo.class);
	}

	@Override
	public String[] getTags() {
		return TagUtil.getTags(TDbScriptInfo.class);
	}
}
