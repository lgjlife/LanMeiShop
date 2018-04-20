package org.lanmei.os.common.rsa;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.junit.Test;

public class RSAUtilTest{
	
	@Test
	public void RsaTest() {
		KeyPair keyPair =RSAKeyFactory.getInstance().getKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
		RSAPrivateKey privatecKey = (RSAPrivateKey)keyPair.getPrivate();
		
		System.out.println("打印密钥..........");
		System.out.println("publicKey = " + publicKey);
		System.out.println("publicKey Modulus = " + publicKey.getModulus());
		System.out.println("publicKey Exponent = " + publicKey.getPublicExponent());
		
		System.out.println("privatecKey = " + privatecKey);
		System.out.println("privatecKey Modulus  = " + privatecKey.getModulus());
		System.out.println("privatecKey Exponent = " + privatecKey.getPrivateExponent());
		
		System.out.println("执行加密操作..........");
		
		String  password = "123456";
		
		System.out.println("加密的密码是 = " + password );
		
		byte[] encryptData = null;
		
		try {
			encryptData = RSAUtilNew.encrypt(publicKey,password.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("加密操作后\r\n" + new String(encryptData));
		
		System.out.println("执行解密操作..........");
		
		byte[] dePassswordByte = null;
				
		try {
			dePassswordByte = RSAUtilNew.decrypt(privatecKey, encryptData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("解操作后\r\n" + new String(dePassswordByte));
	}
	
}