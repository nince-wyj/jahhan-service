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
 * t_db_source:数据源表
 * 自动生成,开发人员请勿修改.
 * @author code-generate-service
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "数据源表")
public class TDbSource extends SuperPojo<TDbSource> {

	private static final long serialVersionUID = 10000000L;
		
	@NotNull(groups = { Create.class, Modify.class, Reset.class }, message = "sourceId不能为空")
	@ApiModelProperty(value = "数据源id")
	private Long sourceId;

	@NotNull(groups = { Create.class, Reset.class }, message = "sourceName不能为空")
	@ApiModelProperty(value = "数据源名称")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "sourceName字符串长度需小于32")
	private String sourceName;

	@NotNull(groups = { Create.class, Reset.class }, message = "modifyTimestamp不能为空")
	@ApiModelProperty(value = "修改时间戳")
	private Long modifyTimestamp;

}