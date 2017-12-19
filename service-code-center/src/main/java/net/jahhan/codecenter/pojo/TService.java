package net.jahhan.codecenter.pojo;

import java.sql.Timestamp;
import java.sql.Date;
import java.math.BigDecimal;

import javax.validation.constraints.*;

import net.jahhan.jdbc.SuperPojo;
import net.jahhan.jdbc.validategroup.*;
import org.hibernate.validator.constraints.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_service:服务表
 * 自动生成,开发人员请勿修改.
 * @author code-generate-service
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务表")
public class TService extends SuperPojo<TService> {

	private static final long serialVersionUID = 10000000L;
		
	@NotNull(groups = { Create.class, Modify.class, Reset.class }, message = "serviceId不能为空")
	@ApiModelProperty(value = "服务id")
	private Long serviceId;

	@NotNull(groups = { Create.class, Reset.class }, message = "serviceCode不能为空")
	@ApiModelProperty(value = "服务代码")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "serviceCode字符串长度需小于32")
	private String serviceCode;

	@NotNull(groups = { Create.class, Reset.class }, message = "serviceName不能为空")
	@ApiModelProperty(value = "服务名称")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "serviceName字符串长度需小于32")
	private String serviceName;

	@NotNull(groups = { Create.class, Reset.class }, message = "creator不能为空")
	@ApiModelProperty(value = "创建者id")
	private Long creator;

	@NotNull(groups = { Create.class, Reset.class }, message = "createTime不能为空")
	@ApiModelProperty(value = "创建时间")
	private Timestamp createTime;

	@NotNull(groups = { Create.class, Reset.class }, message = "status不能为空")
	@ApiModelProperty(value = "状态")
	private Integer status;

	@NotNull(groups = { Create.class, Reset.class }, message = "modifyTimestamp不能为空")
	@ApiModelProperty(value = "修改时间戳")
	private Long modifyTimestamp;

}