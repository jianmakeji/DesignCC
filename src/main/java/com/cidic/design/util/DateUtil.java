package com.cidic.design.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * date与当前时间比较大小，date>当前时间返回true 否则返回false
	 * 
	 * @param date
	 * @return
	 */
	public static boolean compareDate(String date) {
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = df.format(d);
		
		try {
			Date dt1 = df.parse(date);
			Date dt2 = df.parse(dateNowStr);

			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args){
		System.out.println(compareDate("2017-08-30"));
	}
}
