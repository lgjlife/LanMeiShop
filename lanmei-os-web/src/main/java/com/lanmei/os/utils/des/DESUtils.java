package com.lanmei.os.utils.des;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * DFS加密工具类
 * @author lgj
 *
 */
public class DESUtils {
	//任意值，必须为8位，否则报错
	private static String secKey = "12345678";
	
	public static void main(String args[]) {
		//这段是用于测试
		if(false) {
			 //待加密内容
	        String encryptValue = "cryptology";       
	        System.out.println("加密的数据为　＝　" + encryptValue);
	        //执行加密
	        String enresult = DESUtils.encrypt(encryptValue);

	        try {        	
	          //执行解密
	           String decryResult = DESUtils.decrypt(enresult);
	           System.out.println("解密后："+decryResult);
	        } catch (Exception e1) {
	                e1.printStackTrace();
	        }
		}
		if(true) {
			if((args == null) || (args.length < 1)) {
				System.out.println("请输入需要加密的字符");
			}
			else {
				System.out.println("正在执行加密....");
				System.out.println("＋－－－－－－－－－－－－－－－－－－－－－－－－－－－＋");
				for(String arg:args) {
					System.out.println("加密的字符为 = " + arg);
					String enresult = DESUtils.encrypt(arg);
					System.out.println("加密完成");	
					System.out.println("加密后的字符为: " + enresult);
					System.out.println("进行解密测试....");
					String decryResult = null;
					try {        	 
			            decryResult = DESUtils.decrypt(enresult);
			           System.out.println("解密后："+decryResult);
			        } catch (Exception e1) {
			                e1.printStackTrace();
			        }
					if(decryResult.equals(arg)) {
						System.out.println("加密成功");
					}
					else {
						System.out.println("加密失败");
					}
					System.out.println("＋－－－－－－－－－－－－－－－－－－－－－－－－－－－＋");
				}
				
			}
		}
       
   }
   /**
    * 加密
    * @param datasource byte[]
    * @param password String
    * @return byte[]
    */
   public static  String encrypt(String encryptValue) {   
	   
	   Encoder encoder = Base64.getEncoder();   
       
       try{
	       SecureRandom random = new SecureRandom();
	       DESKeySpec desKey = new DESKeySpec(secKey.getBytes());
	       //创建一个密匙工厂，然后用它把DESKeySpec转换成
	       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	       SecretKey securekey = keyFactory.generateSecret(desKey);
	       //Cipher对象实际完成加密操作
	       Cipher cipher = Cipher.getInstance("DES");
	       //用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
	       cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
	       //现在，获取数据并加密
	       //正式执行加密操作
	       byte[] encryptByte = cipher.doFinal(encryptValue.getBytes());
	       return  encoder.encodeToString(encryptByte);
       }catch(Throwable e){
               e.printStackTrace();
       }
       return null;
}
   /**
    * 
    * @param str
    * @return
    * @throws Exception
    */
   public static String decrypt(String decryptValue) throws Exception {
	   
	      Decoder decoder = Base64.getDecoder();
	      byte[] src = decoder.decode(decryptValue);
           // DES算法要求有一个可信任的随机数源
           SecureRandom random = new SecureRandom();
           // 创建一个DESKeySpec对象
           DESKeySpec desKey = new DESKeySpec(secKey.getBytes());
           // 创建一个密匙工厂
           SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//返回实现指定转换的 Cipher 对象
           // 将DESKeySpec对象转换成SecretKey对象
           SecretKey securekey = keyFactory.generateSecret(desKey);
           // Cipher对象实际完成解密操作
           Cipher cipher = Cipher.getInstance("DES");
           // 用密匙初始化Cipher对象
           cipher.init(Cipher.DECRYPT_MODE, securekey, random);
           // 真正开始解密操作
           byte[] decryptByte = cipher.doFinal(src);
           return new String(decryptByte);
       }
}
