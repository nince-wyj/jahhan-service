package ${serviceInfo.codePara["rep_package_path"]};

import java.util.EventObject;
import javax.inject.Singleton;
import ${serviceInfo.codePara["pojo_package_path"]}.${tableInfo.className};

import net.jahhan.cache.constants.RedisConstants;
import net.jahhan.cache.repository.common.AbstractSmpTTLCountRepository;
import net.jahhan.common.extension.constant.BaseConfiguration;
import net.jahhan.jdbc.publish.EventPublisherManager;
import net.jahhan.jdbc.utils.TagUtil;
import net.jahhan.spi.DBEventListener;

/**
 * ${tableInfo.dbTableName}:${tableInfo.comment}
 * 开发人员在此新增缓存操作
 * @author code-generate-service
 */
@Singleton
public class ${tableInfo.className}Rep extends AbstractSmpTTLCountRepository implements DBEventListener{

	private final static String PRE = BaseConfiguration.SERVICE + "_${tableInfo.className}Rep_";
<#if tableInfo.tablePara["cacheExpireTime"]?number gt 0 >

	@Override
	protected int getExistSecond() {
		return ${tableInfo.tablePara["cacheExpireTime"]};
	}
</#if>

	@Override
	protected String getKey(String id) {
		return PRE + id;
	}

	@Override
	protected String getType() {
		return RedisConstants.TABLE_CACHE;
	}
	
	public ${tableInfo.className}Rep() {
		EventPublisherManager.addListener(this);
	}
	
	@Override
	public void listen(EventObject event) {
		onListen(event,${tableInfo.className}.class);
	}

	@Override
	public String[] getTags() {
		return TagUtil.getTags(${tableInfo.className}.class);
	}
}
