package org.lanmei.os.common.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

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
    
    public Map<String, Object> getPublicKeyExpAndMod(RSAPublicKey keyPair){
    	
    	Map<String, Object> publicEM = null;
    	
    	BigInteger publicKeyModulus = keyPair.getModulus();
		BigInteger publicKeyExponent = keyPair.getPublicExponent();
		
    	return publicEM;
    }
    
    public Map<String, Object> getPrivateKeyExpAndMod(RSAPrivateKey keyPair){
    	
    	Map<String, Object> privateEM = null;
    	
    	BigInteger publicKeyModulus = keyPair.getModulus();
		BigInteger publicKeyExponent = keyPair.getPrivateExponent();
		
    	return privateEM;
    }
    
    
    public static void getKeyEm( HashMap<String, String> publicEM1,  HashMap<String, String> privateEM1) {
    	
    	KeyPair keyPair = null;
    	
    	try {
			keyPair = RsaUtils.genKeyPair();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	RSAPublicKey PubKey = (RSAPublicKey)keyPair.getPublic();
		RSAPrivateKey  PriKey = (RSAPrivateKey)keyPair.getPrivate();
		
		BigInteger publicKeyModulus = PubKey.getModulus();
		BigInteger publicKeyExponent = PubKey.getPublicExponent();
		BigInteger privateKeyModulus = PriKey.getModulus();
		BigInteger privateKeyExponent = PriKey.getPrivateExponent();
		
		
		
		/*Map<String, String> publicEM1 = new HashMap<String, String>();
		Map<String, String> privateEM1 = new HashMap<String, String>();*/
		
		Integer vr1 = 1;
		Integer vr2 = 2;
		Integer vr3 = 3;
		Integer vr4 = 4;
		
		publicEM1.put("publicKeyModulus", publicKeyModulus.toString());
		publicEM1.put("publicKeyExponent", publicKeyExponent.toString());
		privateEM1.put("privateKeyModulus", privateKeyModulus.toString());
		privateEM1.put("privateKeyExponent", privateKeyExponent.toString());
		
		/*System.out.println("---公钥 Modulus：" + publicEM1.get("publicKeyModulus"));
		System.out.println("---公钥 Exponent：" + publicEM1.get("publicKeyExponent"));
		System.out.println("---私钥 Modulus：" + privateEM1.get("privateKeyModulus"));
		System.out.println("---私钥 Exponent：" + privateEM1.get("privateKeyExponent"));*/
		
		/*publicEM.put("publicKeyModulus", publicKeyModulus);
		publicEM.put("publicKeyExponent", publicKeyExponent);
		privateEM.put("privateKeyModulus", privateKeyModulus);
		privateEM.put("privateKeyExponent", privateKeyExponent);*/
		
		/*System.out.println("--公钥 Modulus：" + publicEM.get("publicKeyModulus"));
		System.out.println("--公钥 Exponent：" + publicEM.get("publicKeyExponent"));
		System.out.println("--私钥 Modulus：" + privateEM.get("privateKeyModulus"));
		System.out.println("--私钥 Exponent：" + privateEM.get("privateKeyExponent"));*/
    }
}


