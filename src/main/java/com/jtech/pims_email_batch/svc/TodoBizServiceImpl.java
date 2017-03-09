package com.jtech.pims_email_batch.svc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jtech.pims_email_batch.dao.MongoDBDaoImpl;
import com.jtech.pims_email_batch.dto.EmailVO;

@Component
public class TodoBizServiceImpl implements BizService {

	private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	MongoDBDaoImpl mongoDBDaoImpl;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean todoEmailSend() throws AddressException {

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
		emailVO.setContents(contents.toString());

		try {
			emailServiceImpl.sendEmail(emailVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
