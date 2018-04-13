package org.lanmei.os.common.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class RsaUtils {

	/**
	 * 生成密钥对
	 * @param Lentrh 加密长度
	 * @return
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception{  
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		
		return keyPairGenerator.generateKeyPair();
	}
	//公钥加密  
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{  
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        return cipher.doFinal(content);  
    }  
      
    //私钥解密  
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{  
        Cipher cipher=Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        return cipher.doFinal(content);  
    }  
    
  //将base64编码后的公钥字符串转成PublicKey实例  
    public static PublicKey getPublicKey(String modulusStr, String exponentStr) throws Exception{  
        BigInteger modulus=new BigInteger(modulusStr);  
        BigInteger exponent=new BigInteger(exponentStr);  
        RSAPublicKeySpec publicKeySpec=new RSAPublicKeySpec(modulus, exponent);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePublic(publicKeySpec);  
    }  
      
    //将base64编码后的私钥字符串转成PrivateKey实例  
    public static PrivateKey getPrivateKey(String modulusStr, String exponentStr) throws Exception{  
        BigInteger modulus=new BigInteger(modulusStr);  
        BigInteger exponent=new BigInteger(exponentStr);  
        RSAPrivateKeySpec privateKeySpec=new RSAPrivateKeySpec(modulus, exponent);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePrivate(privateKeySpec);  
    }  
}


