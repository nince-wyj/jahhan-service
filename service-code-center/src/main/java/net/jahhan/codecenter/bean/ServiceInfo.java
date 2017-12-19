package net.jahhan.codecenter.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服务信息
 */
@Data
public class ServiceInfo implements Serializable {
	private static final long serialVersionUID = -5944026695379518264L;

	@ApiModelProperty(value = "包路径")
	private String codePackage;

	@ApiModelProperty(value = "服务名")
	private String serviceName;
	
	@ApiModelProperty(value = "代码属性")
	private Map<String, String> codePara = new ConcurrentHashMap<>();

}
