package net.jahhan.codecenter.intf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jahhan.common.extension.constant.ContentType;

@Path("service")
@Consumes({ ContentType.TEXT_PLAIN_UTF_8 })
@Produces({ ContentType.TEXT_PLAIN_UTF_8 })
@Api("代码生成工具")
public interface ServiceIntf {

	@POST
	@Path("{service}/{source}/{version}")
	@ApiOperation(value = "注册数据源名称")
	public void registDbSource(@PathParam(value = "source") String source);
	
	
	@POST
	@Path("{service}/{source}/{version}")
	@ApiOperation(value = "注册数据源配置")
	public void registDbSourceConfig(@PathParam(value = "source") String source, String dbEnvironment, String readDbAddress, 
			String writeDbAddress, String writeAccount, String writePassword, String readAccount, String readPassword);
	
	
	@POST
	@Path("{service}/{source}/{version}")
	@ApiOperation(value = "注册服务名称")
	public void registService(@PathParam(value = "source") String source, @PathParam(value = "service") String service);

	
	@POST
	@Path("{service}/{source}/{version}")
	@ApiOperation(value = "注册服务数据源配置")
	public void registServiceDbConfig(@PathParam(value = "source") String source, @PathParam(value = "service") String service, String codePath);

	

}
