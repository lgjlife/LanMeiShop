package org.lanmei.os.common.rsa;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import org.junit.Test;

public class RsaTest {
	
	@Test
	public void   IrsaTeest() {
		
		KeyPair keyPair = null;
		
		try {
			keyPair = RsaUtils.genKeyPair();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RSAPublicKey PubKey = (RSAPublicKey)keyPair.getPublic();
		RSAPrivateKey  PriKey = (RSAPrivateKey)keyPair.getPrivate();
		System.out.println("公钥是：" + PubKey);
		System.out.println("私钥是：" + PriKey);
		
		BigInteger publicKeyModulus = PubKey.getModulus();
		BigInteger publicKeyExponent = PubKey.getPublicExponent();
		BigInteger privateKeyModulus = PriKey.getModulus();
		BigInteger privateKeyExponent = PriKey.getPrivateExponent();
		
		System.out.println("公钥 Modulus：" + publicKeyModulus);
		System.out.println("公钥 Modulus：" + publicKeyModulus.toString());
		System.out.println("公钥 Exponent：" + publicKeyExponent);
		System.out.println("私钥 Modulus：" + privateKeyModulus);
		System.out.println("私钥 Exponent：" + privateKeyExponent);
		
		/*通过Modulus 和 Exponent 获取公钥和私钥*/
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		try {
			 publicKey =  RsaUtils.getPublicKey(publicKeyModulus.toString(),
														publicKeyExponent.toString());
			 privateKey = RsaUtils.getPrivateKey(privateKeyModulus.toString(),
														privateKeyExponent.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("公钥是：" + publicKey);
		System.out.println("私钥是：" + privateKey);
		
		System.out.println(new String(Base64.getEncoder().encode(PubKey.getEncoded())));
		System.out.println(new String(Base64.getEncoder().encode(PriKey.getEncoded())));
		
		/*加密解密操作*/
		
		String passWord = "zxcvbnm";
		
		System.out.println("加密数据 passWord = " + passWord);
		
		/*加密操作*/
		byte[] encryptedBytes = null;
		byte[] decryptedBytes = null;
		try {
			encryptedBytes = RsaUtils.encrypt(passWord.getBytes(),PubKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			decryptedBytes = RsaUtils.decrypt(encryptedBytes, privateKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("加密后的 encryptedBytes = " + new String(encryptedBytes) );
		System.out.println("解密后的 decryptedBytes = " + new String(decryptedBytes) );
		
		
	}
}
