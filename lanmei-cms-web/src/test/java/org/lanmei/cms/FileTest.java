package org.lanmei.cms;

import java.io.File;

import org.junit.Test;

public class FileTest {

	
	@Test
	public void  myTest() {
		
		File newfile0 = new File("lanmeipath1");
		System.out.println("mkdir = " +  newfile0.mkdirs() + "  path = " + newfile0.getPath());
		System.out.println("" + newfile0.getAbsolutePath());
	}
}
