package br.com.quaemo.support;

import java.util.Calendar;
import java.util.Date;

public class DataUtil {
	
	public static boolean isDataExpirada(Date date) {
		if (date == null) {
			return false;
		}
		Date currentDate = new Date();
		if (date.compareTo(currentDate) < 0) {
			return true;
		}
		return false;
	}

	public static Date addDiasToData(Date date, int amountOfDays) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, amountOfDays);
		return calendar.getTime();
	}

	public static Date addHorasToData(Date date, int amountOfHours) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(10, amountOfHours);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(isDataExpirada(addDiasToData(new Date(), -5)));
		System.out.println(addDiasToData(new Date(), 5));
		System.out.println(addHorasToData(new Date(), 5));
	}
}
