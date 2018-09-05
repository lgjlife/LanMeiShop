package com.lanmei.cms.common.regex;

import java.util.regex.Pattern;

public class ProjectRegex {

	 public static Boolean isTelNum(String TelNum) {
		String regex = "^1[0-9]{10}$";
		return Pattern.matches(regex, TelNum);
	}
	 public static Boolean isEmail(String email) {
			String regex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
			return Pattern.matches(regex, email);
	}
}
