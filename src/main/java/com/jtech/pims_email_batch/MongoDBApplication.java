package com.jtech.pims_email_batch;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jtech.pims_email_batch.dao.MongoDBDaoImpl;
import com.jtech.pims_email_batch.util.DatetimeUtil;

@SpringBootApplication
@ComponentScan("com.jtech")
public class MongoDBApplication {

	private static final Logger logger = LoggerFactory.getLogger(MongoDBApplication.class);

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static void main(String[] args) throws Exception {
//
//		int[] scheduleHours = { 8, 12, 18 };
//
//		if (DatetimeUtil.getValidBatchSchedule(scheduleHours)) {
//			ApplicationContext ctx = SpringApplication.run(MongoDBApplication.class, args);
//			MongoDBDaoImpl bean = (MongoDBDaoImpl) ctx.getBean("mongoDBDaoImpl");
//
//			Map paramMap = new HashMap();
//			paramMap.put("complete", false);
//
//			// logger.debug(">>>>> >>>>> >>>>> " +
//			// bean.selectOneByGson("testOut",
//			// paramMap));
//
//			List resultList = bean.selectList("todo", paramMap);
//
//			for (int i = 0; i < resultList.size(); i++) {
//
//				Map rowMap = (Map) resultList.get(i);
//				Gson gson = new GsonBuilder().create();
//
//				logger.debug(">>>>> >>>>> >>>>> title: " + rowMap.get("title"));
//
//				logger.debug(">>>>> >>>>> >>>>> due_date: " + rowMap.get("due_date"));
//			}
//
//		} else {
//			logger.debug(">>>>> >>>>> >>>>> not a time to run");
//
//		}
//
//	}

}
