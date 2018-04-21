package org.lanmei.os.common.regex;

import org.junit.Test;

public class ProjectRegexTest {

	 @Test
	 public  void RegexTest() {
		 System.out.println("isTelNum = " + ProjectRegex.isTelNum("12345678963"));
		 System.out.println("isTelNum = " + ProjectRegex.isTelNum("22345678963"));
		 System.out.println("isTelNum = " + ProjectRegex.isEmail("1234@5678963"));
		 System.out.println("isTelNum = " + ProjectRegex.isEmail("22345678963"));
	 }
}
