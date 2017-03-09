package com.jtech.pims_email_batch;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.jtech.pims_email_batch.dto.EmailVO;
import com.jtech.pims_email_batch.svc.EmailServiceImpl;

@SpringBootApplication
@ComponentScan("com.jtech")
public class EmailApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailApplication.class);

//	public static void main(String[] args) throws Exception {
//		ApplicationContext ctx = SpringApplication.run(MongoDBApplication.class, args);
//		EmailServiceImpl bean = (EmailServiceImpl) ctx.getBean("emailServiceImpl");
//		
//		EmailVO emailVO = new EmailVO();
//		
//		Address[] address = {new InternetAddress("jmko79@gmail.com")};
//		emailVO.setToEmail(address);
//		emailVO.setFromName("tester");
//		emailVO.setSubject("test email");
//		emailVO.setContents("test contents");
//		
//		if(bean.sendEmail(emailVO)) {
//			logger.debug(">>>>> >>>>> >>>>> email sent");
//		} else {
//			logger.debug(">>>>> >>>>> >>>>> email sending did not go well");
//		}
//	}
	
}
