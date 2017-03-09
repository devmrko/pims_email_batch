package com.jtech.pims_email_batch.svc;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jtech.pims_email_batch.dto.EmailVO;

@Component
public class EmailServiceImpl implements BizService {

	private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Value("${mail.smtp.host}")
	String host;

	@Value("${mail.smtp.port}")
	String port;

	@Value("${mail.smtp.starttls.enable}")
	String starttls;

	@Value("${mail.transport.protocol}")
	String protocol;

	@Value("${mail.smtp.auth}")
	String sauth;

	@Value("${mail.smtp.id}")
	String id;

	@Value("${mail.smtp.pw}")
	String pw;

	@Value("${mail.smtp.timeout}")
	String timeout;

	public boolean sendEmail(EmailVO emailVO) throws Exception {
		boolean sendResult = false;

		logger.debug(">>>>> >>>>> >>>>> sendEmail");

		try {
			// email properties
			Properties props = new Properties();

			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.starttls.enable", starttls);
			props.put("mail.transport.protocol", protocol);
			props.put("mail.smtp.auth", sauth);
			props.put("mail.smtp.connectiontimeout", timeout);
			props.put("mail.smtp.timeout", timeout);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(id, pw);
				}
			});

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(id, MimeUtility.encodeText(emailVO.getFromName(), "UTF-8", "B")));

			msg.setRecipients(Message.RecipientType.TO, emailVO.getToEmail());

			msg.setSubject(emailVO.getSubject());
			msg.setSentDate(new java.util.Date());
			msg.setContent(emailVO.getContents(), "text/html;charset=euc-kr");

			Transport.send(msg); // send an email
			sendResult = true;

		} catch (Exception e) {
			logger.error("<<<<< <<<<< <<<<< EmailService - sendEmail - Exception: " + e.getMessage());

		}

		return sendResult;

	}

}
