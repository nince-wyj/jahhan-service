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
 * t_service_source_conf:服务数据源配置表
 * 自动生成,开发人员请勿修改.
 * @author code-generate-service
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务数据源配置表")
public class TServiceSourceConf extends SuperPojo<TServiceSourceConf> {

	private static final long serialVersionUID = 10000000L;
		
	@NotNull(groups = { Create.class, Modify.class, Reset.class }, message = "serviceSourceConfId不能为空")
	@ApiModelProperty(value = "数据源id")
	private Long serviceSourceConfId;

	@NotNull(groups = { Create.class, Reset.class }, message = "serviceId不能为空")
	@ApiModelProperty(value = "数据源名称")
	private Long serviceId;

	@NotNull(groups = { Create.class, Reset.class }, message = "sourceId不能为空")
	@ApiModelProperty(value = "数据源写库url")
	private Long sourceId;

	@NotNull(groups = { Create.class, Reset.class }, message = "codePath不能为空")
	@ApiModelProperty(value = "代码生成目录")
	@Length(min = 0, max = 128, groups = { Create.class, Modify.class, Reset.class }, message = "codePath字符串长度需小于128")
	private String codePath;

	@NotNull(groups = { Create.class, Reset.class }, message = "isDefault不能为空")
	@ApiModelProperty(value = "是否默认数据源")
	private Integer isDefault;

	@NotNull(groups = { Create.class, Reset.class }, message = "modifyTimestamp不能为空")
	@ApiModelProperty(value = "修改时间戳")
	private Long modifyTimestamp;

}