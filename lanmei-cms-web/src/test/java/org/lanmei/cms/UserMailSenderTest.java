package org.lanmei.cms;

import org.junit.Test;
import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.cms.email.UserMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;

public class UserMailSenderTest {

	private final static Logger logger = LoggerFactory.getLogger("UserMailSenderTest.class");	
	//@Test
	public void emailTest() {
		
		logger.debug("创键admin......");
		CmsAdmin  admin = new CmsAdmin();
		logger.debug("创键admin成功");
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-email.xml");
		
		UserMailSender sender = (UserMailSender) ac.getBean("userMailSender");
		
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("563739007@qq.com");//收件人邮箱地址
        mail.setFrom("lanmeishop1@sina.com");//收件人
        mail.setSubject("spring自带javamail发送的邮件");//主题
        mail.setText("hello this mail is from spring javaMail ");//正文
        sender.send(mail);
	}
	
}
