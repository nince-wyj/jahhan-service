package net.jahhan.codecenter.constant;

import lombok.Data;

/**
 * 数据库连接配置
 */
@Data
public class DbParam {
    //获得url  
    private String url;  
    //获得连接数据库的用户名  
    private String user;  
	//获得连接数据库的密码  
    private String password;
}
