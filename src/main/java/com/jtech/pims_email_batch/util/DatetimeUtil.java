package com.jtech.pims_email_batch.util;

import java.time.LocalDateTime;

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
}
