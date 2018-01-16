package com.boc.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源常量
 * <p>@Title DataSourceConst</p>
 * <p>@Description com.dgg.message.xa.utils</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年7月3日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public enum DataSourceEnums {
	PT_UPMS("upms"),
	PT_GOODS("goods"),
	PT_BUSINESS("business"),
	PT_MEMBER("member"),
	PT_OPERATE("operate"),
	PT_ORDER("order"),
	PT_CMS("cms"),
	PT_ARCHIVE("archive"),
	PT_SCHEDULE("schedule"),
	PT_WX("wx");
	
	// 成员变量
	public String key;

	// 构造方法
	private DataSourceEnums(String key) {
		this.key = key;
	}

	private static final Map<String, DataSourceEnums> stringToEnum = new HashMap<String, DataSourceEnums>();
	static {
		for (DataSourceEnums blah : values()) {
			stringToEnum.put(blah.key, blah);
		}
	}

	public static DataSourceEnums toEnum(String symbol) {
		return stringToEnum.get(symbol);
	}
}
