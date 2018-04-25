package org.lanmei.cms.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class UserMailSender {

	
	private JavaMailSenderImpl   mailSender;
	
	public void send(SimpleMailMessage mail) {
		mailSender.send(mail);
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}	
}
