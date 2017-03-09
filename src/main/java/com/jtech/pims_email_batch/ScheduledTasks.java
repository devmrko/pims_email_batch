package com.jtech.pims_email_batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jtech.pims_email_batch.svc.TodoBizServiceImpl;
import com.jtech.pims_email_batch.util.DatetimeUtil;

@Component
public class ScheduledTasks {

	private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private TodoBizServiceImpl todoBizServiceImpl;

	// 1000 = 1 second
	@Scheduled(fixedRate = 60000)
	public void runBatchWork() throws Exception {
		logger.info(">>>>> The time is now " + dateFormat.format(new Date()));

		int[] scheduleHours = { 8, 9, 10, 11, 13, 14, 15, 16, 17, 20, 21 };

		if (DatetimeUtil.getValidBatchSchedule(scheduleHours)) {
			todoBizServiceImpl.todoEmailSend();
		}

	}

}
