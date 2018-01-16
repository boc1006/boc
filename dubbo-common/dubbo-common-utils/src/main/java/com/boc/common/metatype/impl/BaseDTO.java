package com.boc.common.metatype.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boc.common.metatype.DTO;
import com.boc.common.metatype.util.TypeCaseHelper;
import com.boc.common.utils.JsonHelper;

/**
 * 数据传输对象(DateTransferObject)<br>
 * 建议在参数传递过程中尽量使用Dto来传递<br>
 *
 * @since 2009-06-23
 * @see DTO
 * @see java.io.Serializable
 */
public class BaseDTO extends HashMap implements DTO, Serializable {

	private static final long serialVersionUID = 1101684508376473931L;

	public BaseDTO(){}

	public BaseDTO(Map m){
		super(m);
	}

	public BaseDTO(String key, Object value){
		put(key, value);
	}

	/**
	 * 以BigDecimal类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return BigDecimal 键值
	 */
	public BigDecimal getAsBigDecimal(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
		if (obj != null)
			return (BigDecimal) obj;
		else
			return null;
	}

	/**
	 * 以Date类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Date 键值
	 */
	public Date getAsDate(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}

	/**
	 * 以Integer类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Integer 键值
	 */
	public Integer getAsInteger(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
		if (obj != null)
			return (Integer) obj;
		else
			return null;
	}

	/**
	 * 以Long类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Long 键值
	 */
	public Long getAsLong(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Long", null);
		if (obj != null)
			return (Long) obj;
		else
			return null;
	}

	/**
	 * 以String类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return String 键值
	 */
	public String getAsString(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "String", null);
		if (obj != null)
			return (String) obj;
		else
			return "";
	}
	public String getString(Object key) {
		return (String)get(key);
	}
	/**
	 * 以List类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return List 键值
	 */
	public List getAsList(String key){
		return (List)get(key);
	}

	/**
	 * 以Timestamp类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	public Timestamp getAsTimestamp(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
		if (obj != null)
			return (Timestamp) obj;
		else
			return null;
	}

	/**
	 * 以Boolean类型返回键值
	 *
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	public Boolean getAsBoolean(String key){
		Object obj = TypeCaseHelper.convert(get(key), "Boolean", null);
		if (obj != null)
			return (Boolean) obj;
		else
			return null;
	}

	/**
	 * 将此Dto对象转换为Json格式字符串<br>
	 *
	 * @return string 返回Json格式字符串
	 */
	public String toJson() {
		String strJson = null;
		strJson = JsonHelper.encodeObject2Json(this);
		return strJson;
	}

	/**
	 * 将此Dto对象转换为Json格式字符串(带日期时间型)<br>
	 *
	 * @return string 返回Json格式字符串
	 */
	public String toJson(String pFormat){
		String strJson = null;
		strJson = JsonHelper.encodeObject2Json(this, pFormat);
		return strJson;
	}

	/**
	 * 打印DTO对象
	 *
	 */
	public void println(){
		System.out.println(this);
	}

//	public String getToken() {
//		return getAsString(HttpServiceAdapter.tokenKey);
//	}
}
