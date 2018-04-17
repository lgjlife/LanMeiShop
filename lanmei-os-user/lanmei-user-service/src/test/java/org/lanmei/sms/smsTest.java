package org.lanmei.sms;

import org.junit.Test;

public class smsTest {	
	@Test
	public void sms(){
		AliSms.userSendSms("13925716752","123456");
	}
	
}
