package shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

public class EncryptAndDecrypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String user = "liangguojian";
		String salt = "123456";
		String md5 = new Md5Hash(user,salt).toString();
		
		System.out.println(md5);
		//0171c1f21b646c7ee0c2ffc0ab5abeb8
		//ac6c0a6d3768917929aaaa1d0b18a80f
	}

}
