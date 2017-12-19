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
 * t_db_script_info:数据源建表信息表
 * 自动生成,开发人员请勿修改.
 * @author code-generate-service
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "数据源建表信息表")
public class TDbScriptInfo extends SuperPojo<TDbScriptInfo> {

	private static final long serialVersionUID = 10000000L;
		
	@NotNull(groups = { Create.class, Modify.class, Reset.class }, message = "dbScriptInfoId不能为空")
	@ApiModelProperty(value = "数据库建表信息id")
	private Long dbScriptInfoId;

	@ApiModelProperty(value = "执行语句版本号")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "version字符串长度需小于32")
	private String version;

	@NotNull(groups = { Create.class, Reset.class }, message = "sourceId不能为空")
	@ApiModelProperty(value = "数据源id")
	private Long sourceId;

	@NotNull(groups = { Create.class, Reset.class }, message = "creator不能为空")
	@ApiModelProperty(value = "创建者")
	private Long creator;

	@NotNull(groups = { Create.class, Reset.class }, message = "createTime不能为空")
	@ApiModelProperty(value = "创建时间")
	private Timestamp createTime;

	@NotNull(groups = { Create.class, Reset.class }, message = "content不能为空")
	@ApiModelProperty(value = "执行语句")
	@Length(min = 0, max = 65535, groups = { Create.class, Modify.class, Reset.class }, message = "content字符串长度需小于65,535")
	private String content;

	@NotNull(groups = { Create.class, Reset.class }, message = "status不能为空")
	@ApiModelProperty(value = "修改状态(0:提交，1：执行错误，2：执行成功，3：代码生成完成)")
	private Integer status;

	@NotNull(groups = { Create.class, Reset.class }, message = "modifyTimestamp不能为空")
	@ApiModelProperty(value = "修改时间戳")
	private Long modifyTimestamp;

}