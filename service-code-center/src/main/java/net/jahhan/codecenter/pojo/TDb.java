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
 * t_db:数据库表
 * 自动生成,开发人员请勿修改.
 * @author code-generate-service
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "数据库表")
public class TDb extends SuperPojo<TDb> {

	private static final long serialVersionUID = 10000000L;
		
	@NotNull(groups = { Create.class, Modify.class, Reset.class }, message = "dbId不能为空")
	@ApiModelProperty(value = "数据库id")
	private Long dbId;

	@NotNull(groups = { Create.class, Reset.class }, message = "sourceId不能为空")
	@ApiModelProperty(value = "数据源id")
	private Long sourceId;

	@NotNull(groups = { Create.class, Reset.class }, message = "dbName不能为空")
	@ApiModelProperty(value = "数据库名称")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "dbName字符串长度需小于32")
	private String dbName;

	@NotNull(groups = { Create.class, Reset.class }, message = "dbEnvironment不能为空")
	@ApiModelProperty(value = "数据库环境")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "dbEnvironment字符串长度需小于32")
	private String dbEnvironment;

	@NotNull(groups = { Create.class, Reset.class }, message = "writeUrl不能为空")
	@ApiModelProperty(value = "数据库写库url")
	@Length(min = 0, max = 128, groups = { Create.class, Modify.class, Reset.class }, message = "writeUrl字符串长度需小于128")
	private String writeUrl;

	@NotNull(groups = { Create.class, Reset.class }, message = "writeUser不能为空")
	@ApiModelProperty(value = "数据库写库用户名")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "writeUser字符串长度需小于32")
	private String writeUser;

	@NotNull(groups = { Create.class, Reset.class }, message = "writePassword不能为空")
	@ApiModelProperty(value = "数据库写库密码")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "writePassword字符串长度需小于32")
	private String writePassword;

	@NotNull(groups = { Create.class, Reset.class }, message = "readUrl不能为空")
	@ApiModelProperty(value = "数据库读库url")
	@Length(min = 0, max = 128, groups = { Create.class, Modify.class, Reset.class }, message = "readUrl字符串长度需小于128")
	private String readUrl;

	@NotNull(groups = { Create.class, Reset.class }, message = "readUser不能为空")
	@ApiModelProperty(value = "数据库读库用户名")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "readUser字符串长度需小于32")
	private String readUser;

	@NotNull(groups = { Create.class, Reset.class }, message = "readPassword不能为空")
	@ApiModelProperty(value = "数据库读库密码")
	@Length(min = 0, max = 32, groups = { Create.class, Modify.class, Reset.class }, message = "readPassword字符串长度需小于32")
	private String readPassword;

	@NotNull(groups = { Create.class, Reset.class }, message = "modifyTimestamp不能为空")
	@ApiModelProperty(value = "修改时间戳")
	private Long modifyTimestamp;

}