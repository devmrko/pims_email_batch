package com.jtech.pims_email_batch.svc;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jtech.pims_email_batch.dao.MongoDBDaoImpl;
import com.jtech.pims_email_batch.dto.EmailVO;
import com.jtech.pims_email_batch.util.DatetimeUtil;

@Component
public class TodoBizServiceImpl implements BizService {

	private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	MongoDBDaoImpl mongoDBDaoImpl;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean todoEmailSend() throws AddressException, ParseException {

		EmailVO emailVO = new EmailVO();
		Address[] address = { new InternetAddress("jmko79@gmail.com") };
		emailVO.setToEmail(address);
		emailVO.setFromName("todo manager");
		emailVO.setSubject("[Today's todo list]");
		StringBuffer contents = new StringBuffer();

		Map paramMap = new HashMap();
		paramMap.put("complete", false);

		List resultList = mongoDBDaoImpl.selectList("todo", paramMap);
		Map rowMap = null;

		for (int i = 0; i < resultList.size(); i++) {
			rowMap = (Map) resultList.get(i);
			contents.append((i + 1) + "th todo: " + rowMap.get("title"));
			contents.append("<p>");
		}

		paramMap.put("complete", "n");
		resultList = mongoDBDaoImpl.selectList("checklist", paramMap);
		// String keyVal = null;
		// DateFormat mongoDBDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		// Date dateOfToday = new Date();
		// Date todayByMongoDBFormat =
		// mongoDBDateFormat.parse(mongoDBDateFormat.format(dateOfToday));

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		int chekcklistCnt = 0;
		String intervalUnit;
		double intervalVal, calIntervalVal;
		for (int i = 0; i < resultList.size(); i++) {

			calendar.setTime(date);
			rowMap = (Map) resultList.get(i);

			intervalUnit = String.valueOf(rowMap.get("interval_unit"));
			if (DatetimeUtil.isNumeric(String.valueOf(rowMap.get("interval_val")))) {
				intervalVal = Double.valueOf(String.valueOf(rowMap.get("interval_val")));
			} else {
				intervalVal = 0.0;
			}

			if ("day".equals(intervalUnit)) {
				calIntervalVal = Integer.valueOf((int) Math.floor(intervalVal / 2));
				calendar.add(Calendar.DATE, (int) -calIntervalVal);
			} else if ("month".equals(intervalUnit)) {
				calIntervalVal = Integer.valueOf((int) Math.floor((intervalVal * 30) / 2));
				calendar.add(Calendar.DATE, (int) -calIntervalVal);
			} else if ("year".equals(intervalUnit)) {
				calIntervalVal = Integer.valueOf((int) Math.floor((intervalVal * 365) / 2));
				calendar.add(Calendar.DATE, (int) -calIntervalVal);
			}

			String _id = String.valueOf(rowMap.get("_id"));

			Map query = new HashMap();
			query.put("chklst_id", new ObjectId(_id));
			query.put("done_bool", true);
			query.put("due_date", new HashMap() {
				{
					put("$gte", DatetimeUtil.returnStrDateFromDate(calendar));
					put("$ne", "");
				}
			});

			if (mongoDBDaoImpl.selectList("checklistDtl", query).size() == 0) {
				if (chekcklistCnt == 0) {
					contents.append("<p>");
					contents.append("<p>");
					contents.append("[Today's checklist]");
					contents.append("<p>");
				}
				contents.append((chekcklistCnt + 1) + "th checklist: " + rowMap.get("title"));
				contents.append("<p>");

				chekcklistCnt++;
			}

		}

		emailVO.setContents(contents.toString());

//		logger.debug(contents.toString());

		try {
			emailServiceImpl.sendEmail(emailVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
