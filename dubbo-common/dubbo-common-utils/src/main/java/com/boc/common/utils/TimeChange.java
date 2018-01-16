package com.boc.common.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeChange {
	public static List<String> timesDay(String endtime) throws IOException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar 
		= Calendar.getInstance();
		List<String> list = new ArrayList<String>();
			Date date = sdf.parse(endtime);
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			for(int i=0;i<8;i++){
				Date dt = calendar.getTime();
				String str = sdf.format(dt);
				list.add(str);
				calendar.add(Calendar.DAY_OF_YEAR, -1);
			}
		return list;
}
	public static String timesMon(String endtime) throws IOException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        Date date = null;  
        try {  
            date = sdf.parse(endtime);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        Calendar cl = Calendar.getInstance();  
        cl.setTime(date);  
        cl.add(Calendar.MONTH, -5);  
        date = cl.getTime();  
        String mon= sdf.format(date); 
        return mon;
	}
//	public static List<String> timesWeek(String endtime) throws IOException, ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar 
//		= Calendar.getInstance();
//		List<String> list = new ArrayList<String>();
//			Date date = sdf.parse(endtime);
//			calendar.setTime(date);
//			calendar.add(Calendar.MONTH, 1);
//			for(int i=0;i<5;i++){
//				Date dt = calendar.getTime();
//				String str = sdf.format(dt);
//				list.add(str);
//				calendar.add(Calendar.WEEK_OF_YEAR, -1);
//			}
//		return list;
//}
	public static List<String> monss(String endtime) throws IOException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar 
		= Calendar.getInstance();
		List<String> list = new ArrayList<String>();
			Date date = sdf.parse(endtime);
			calendar.setTime(date);
			for(int i=0;i<6;i++){
				Date dt = calendar.getTime();
				String str = sdf.format(dt);
				list.add(str);
				calendar.add(Calendar.MONTH, -1);
			}
		return list;
}
	public static String timeDays(String endtime) throws IOException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = null;  
        try {  
            date = sdf.parse(endtime);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        Calendar cl = Calendar.getInstance();  
        cl.setTime(date);  
        cl.add(Calendar.DAY_OF_YEAR, 1);  
        date = cl.getTime();  
        String mon= sdf.format(date); 
        return mon;
	}
	
	public static String getLastDayOfMonth(int year,int month)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		
		return lastDayOfMonth;
	}

public static void main(String[] args) throws IOException, ParseException {
	String li = TimeChange.timeDays("2019-09-10");
	System.out.println(li);
}
}
