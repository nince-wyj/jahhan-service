package net.jahhan.codecenter.vo;

import java.io.Serializable;

import javax.validation.constraints.*;
//import com.frameworkx.constraints.*;
import org.hibernate.validator.constraints.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "表名")
public class Live implements Serializable {
	
	private static final long serialVersionUID = -7432953290772366942L;
	
	@NotNull(message = "liveId不能为空")
	@ApiModelProperty(value = "生命周期id")
	@Email
	private Long liveId;
	
	@ApiModelProperty(value = "名称")
	private String name;
	
	@ApiModelProperty(value = "值")
	private String value;
	
	@ApiModelProperty(value = "顺序")
	private Integer orderNum;

}