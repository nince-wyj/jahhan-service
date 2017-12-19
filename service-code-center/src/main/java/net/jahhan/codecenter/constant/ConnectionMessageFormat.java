package net.jahhan.codecenter.constant;

import java.text.MessageFormat;

public enum ConnectionMessageFormat {

	JdbcConnectionUrl("jdbc:{0}://{1}/{2}?useUnicode=true&characterEncoding=utf-8");

	private String pattern;

	ConnectionMessageFormat(String pattern) {
		this.pattern = pattern;
	}

	public String format(Object... params) {
		return MessageFormat.format(pattern, params);
	}
}
