package com.boc.common.metatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface DTO  extends Map,Serializable{
	/**
	 * 以Integer类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Integer 键值
	 */
	public Integer getAsInteger(String pStr);

	/**
	 * 以Long类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Long 键值
	 */
	public Long getAsLong(String pStr);

	/**
	 * 以String类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return String 键值
	 */
	public String getAsString(String pStr);
	public String getString(Object key);
	/**
	 * 取出属性值
	 *
	 * @param pStr
	 *            属性Key
	 * @return Integer
	 */
	public BigDecimal getAsBigDecimal(String pStr);

	/**
	 * 取出属性值
	 *
	 * @param pStr
	 *            :属性Key
	 * @return Integer
	 */
	public Date getAsDate(String pStr);

	/**
	 * 以List类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return List 键值
	 */
	public List getAsList(String key);

	/**
	 * 以Timestamp类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	public Timestamp getAsTimestamp(String pStr);

	/**
	 * 以Boolean类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	public Boolean getAsBoolean(String key);

	/**
	 * 将此Dto对象转换为Json格式字符串<br>
	 *
	 * @return string 返回Json格式字符串
	 */
	public String toJson();

	/**
	 * 打印DTO对象<br>
	 *
	 */
	public void println();

	/**
	 * 将此Dto对象转换为Json格式字符串(带日期时间型)<br>
	 *
	 * @return string 返回Json格式字符串
	 */
	public String toJson(String pFormat);

}
