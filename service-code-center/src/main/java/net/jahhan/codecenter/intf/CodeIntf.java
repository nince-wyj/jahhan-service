package net.jahhan.codecenter.intf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jahhan.common.extension.constant.ContentType;

@Path("code")
@Consumes({ ContentType.TEXT_PLAIN_UTF_8 })
@Produces({ ContentType.TEXT_PLAIN_UTF_8 })
@Api("代码生成工具")
public interface CodeIntf {

	@POST
	@Path("service/{service}/source/{source}/version/{version}")
	@ApiOperation(value = "注册sql建表脚本")
	public void uploadSql(@PathParam(value = "service") String service, 
			@PathParam(value = "source") String source,
			@PathParam(value = "version") String version,	
			String sql);
	
	
	@POST
	@Path("scriptId/{scriptId}")
	@ApiOperation(value = "创建表")
	public void excuteSql(String scriptId);
	
	
}
