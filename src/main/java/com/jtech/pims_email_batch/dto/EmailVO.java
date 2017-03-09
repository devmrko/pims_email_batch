package com.jtech.pims_email_batch.dto;

import javax.mail.Address;

public class EmailVO {
	private String subject;
	private String contents;
	private String fromName;
	private Address[] toEmails;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Address[] getToEmail() {
		return toEmails;
	}

	public void setToEmail(Address[] toEmails) {
		this.toEmails = toEmails;
	}

}
