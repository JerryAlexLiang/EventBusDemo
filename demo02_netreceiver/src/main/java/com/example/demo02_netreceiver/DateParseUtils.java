package com.example.demo02_netreceiver;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建日期：2018/2/22 on 下午8:36
 * 描述: 时间转换器
 * 作者: liangyang
 */
public class DateParseUtils {

	/**
	 * 时间转换成时间戳
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	public static long dateToStamp(String time) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateToStamp(time, simpleDateFormat);
	}

	/**
	 * 时间转换成时间戳
	 * 
	 * @param time
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static long dateToStamp(String time, SimpleDateFormat dateFormat)
			throws ParseException {
		Date date = dateFormat.parse(time);
		return date.getTime();
	}

	/**
	 * 将时间戳转换为时间
	 * 
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String stampToDate(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return stampToDate(time,simpleDateFormat);
	}

	/**
	 * 将时间戳转换为时间
	 * 
	 * @param time
	 * @param dateFormat
	 * @return
	 */
	public static String stampToDate(long time, SimpleDateFormat dateFormat) {
		Date date = new Date(time);
		return dateFormat.format(date);
	}

	/**
	 * 将字符串转换为时间
	 * @param dateString
	 * @return
	 */
	public static Date stringToDate(String dateString) {
		ParsePosition position = new ParsePosition(0);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		Date dateValue = simpleDateFormat.parse(dateString, position);
		return dateValue;
	}

}
