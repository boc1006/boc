package com.boc.common.utils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 日期转换为yyyyMMdd格式的Long
	 * @param date 日期
	 * @return
	 */
	public static Long dateToLong(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dateStr = df.format(date);
		return new Long(dateStr);
	}

	/**
	 * 将Long类型格式为yyyyMMdd的日期转为yyyy－MM－dd
	 * @param dateLong 日期
	 * @return
	 */
	public static String dateLongFormatString(Long dateLong){
		if(null == dateLong){
			return "";
		}
		String value = dateLong.toString();
		if(value.length() < 8){
			return "";
		}
		StringBuffer dateStr = new StringBuffer();
		dateStr.append(value.substring(0, 4));
		dateStr.append("-");
		dateStr.append(value.substring(4, 6));
		dateStr.append("-");
		dateStr.append(value.substring(6, 8));
		return dateStr.toString();
	}

	/**
	 * 日期转换为yyyyMMdd格式的Long
	 * @return
	 */
	public static Long dateToLongByNewDate(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dateStr = df.format(date);
		return new Long(dateStr);
	}

	/**
	 * 日期转换为yyyyMMdd格式的Long
	 * @param date 日期
	 * @return
	 */
	public static Long dateTimeToLong(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = df.format(date);
		return new Long(dateStr);
	}

	/**
	 * 将Long类型格式为yyyyMMddHHmmss的日期转为yyyy－MM－dd
	 * @param timeLong 日期
	 * @return
	 */
	public static String timeLongFormatString(Long timeLong){
		if(null == timeLong){
			return "";
		}
		String value = timeLong.toString();
		if(value.length() < 14){
			return "";
		}
		StringBuffer dateStr = new StringBuffer();
		dateStr.append(value.substring(0, 4));
		dateStr.append("-");
		dateStr.append(value.substring(4, 6));
		dateStr.append("-");
		dateStr.append(value.substring(6, 8));
		dateStr.append("");
		dateStr.append(value.substring(8, 10));
		dateStr.append(":");
		dateStr.append(value.substring(10, 12));
		dateStr.append(":");
		dateStr.append(value.substring(12, 14));
		return dateStr.toString();
	}

	/**
	 * 日期转换为yyyyMMddhhmmss格式的Long
	 * @return
	 */
	public static Long dateTimeToLongByNewDate(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = df.format(date);
		return new Long(dateStr);
	}
	/**
	 * 日期转换为yyyyMMdd格式的Long
	 * @return
	 */
	public static Long dateTimeToLongByNewDate2(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dateStr = df.format(date);
		return new Long(dateStr);
	}
	/**
	 * 将String 类型的时间转换为yyyyMMdd格式的Long
	 */
	public static Long stringTimeToLong3(String str){
		String[] temp = str.split("-");
		StringBuilder joinStr =new StringBuilder(200);
		for (int i = 0; i < temp.length; i++) {
			joinStr .append(temp[i]);
		}
		Long longDate = Long.parseLong(joinStr.toString());
		return longDate;
	}
}
