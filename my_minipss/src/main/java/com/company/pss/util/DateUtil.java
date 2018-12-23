package com.company.pss.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date beginDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//将时间装换为0：0:：0
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();
		return time;
	}
	
	public static Date endDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//将时间装换为0：0:：0
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date time = calendar.getTime();
		return time;
	}
	
	public static void main(String[] args) {
		//System.out.println(new Date());
		//System.out.println(endDateTime(new Date()));
	}
}
