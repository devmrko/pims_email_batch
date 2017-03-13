package com.jtech.pims_email_batch.util;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jtech.pims_email_batch.ScheduledTasks;

public class DatetimeUtil {

	private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	public static boolean getValidBatchSchedule(int[] scheduleHours) {
		LocalDateTime currentTime = LocalDateTime.now();

		logger.debug(
				">>>>> >>>>> >>>>> Current hour & minute: " + currentTime.getHour() + ":" + currentTime.getMinute());

		if (0 == currentTime.getMinute()) {
			for (int i = 0; i < scheduleHours.length; i++) {
				if (scheduleHours[i] == currentTime.getHour()) {
					return true;
				}
			}
		}
		return false;
	}

	public static String returnStrDateFromDate(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR); // 2012
		int month = calendar.get(Calendar.MONTH) + 1; // 9 - October!!!
		int day = calendar.get(Calendar.DAY_OF_MONTH); // 5

		StringBuffer dateOfTodayStr = new StringBuffer();
		dateOfTodayStr.append(String.valueOf(year));
		dateOfTodayStr.append("-");
		dateOfTodayStr.append(String.format("%02d", month));
		dateOfTodayStr.append("-");
		dateOfTodayStr.append(String.format("%02d", day));

		return dateOfTodayStr.toString();
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
