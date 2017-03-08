package com.jtech.pims_email_batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	protected Logger log = Logger.getLogger(getClass());

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// @Autowired
	// private StoreBizSvcImpl storeBizSvcImpl;

	// 1000 = 1 second
	@Scheduled(fixedRate = 60000)
	public void runBatchWork() throws Exception {
		log.info(">>>>> The time is now " + dateFormat.format(new Date()));
	}

}
