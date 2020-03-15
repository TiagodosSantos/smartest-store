package com.smartest.store.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * This class is used to manipulate Date
 * @author Tiago Santos
 * @since   2020-03-15
 * 
 */
public class DateUtils {

	private static DateUtils singleton;

	private DateUtils() {
	}

	/**
	 * Create singleton instance
	 * 
	 * @return DateUtils.
	 */
	public static DateUtils getInstance() {

		if (singleton == null) {
			singleton = new DateUtils();
		}
		return singleton;
	}



	/**
	 * Return string date according to format required
	 * @param date 
	 * @param format
	 *            String Format may be: 
	 *            (DEFAULT) dd/MM/yyyy HH:mm:ss (24h) HH:mm (24h) HH:mm:ss (Date) dd/MM/yyyy
	 * 
	 * @return String date converted to string
	 * @throws Exception
	 */
	public String dateToString(Date date, String format) {
		
		if(date == null)
			return "Date not specified";

		// Default
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (!StringUtils.isEmpty(format))
			formatter = new SimpleDateFormat(format);

		String stringDate = "";
		try {
			stringDate = formatter.format(date);
		} catch (Exception e) {
			System.out.println("Error while converting date to string"+ e);
		}

		return stringDate;

	}

	public String dateToString(Calendar date, String format) {
		if(date == null)
			return "Date not specified";
		
		return dateToString(date.getTime(), format);
	}

}
