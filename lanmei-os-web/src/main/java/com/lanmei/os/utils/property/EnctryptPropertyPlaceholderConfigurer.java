package com.lanmei.os.utils.property;

import com.lanmei.os.utils.des.DESUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EnctryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	//定义需要解密的属性
	private String[] enctryptPropertyValue= {"jdbc.username","jdbc.password"};
	/**
	 * 在加载属性配置文件并读取配置属性时都会调用该方法，可以对所有的值进行转换，返回的是新的propertyValue
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		System.out.println(propertyName + " = " + propertyValue );
		if(isEnctryptPropertyValue(propertyName)) {
			
			String decryResult = null;
			try {       
				//解密操作
	            decryResult = DESUtils.decrypt(propertyValue);
	           System.out.println("解密后："+decryResult);
	        } catch (Exception e1) {
	                e1.printStackTrace();
	        }
			//返回解密后的属性值
			return (decryResult);
		}
		return propertyValue;
	} 
	/**
	 *　判断是否需要解密
	 * @param value
	 * @return
	 */
	private boolean isEnctryptPropertyValue(String  value){
		
		for(String propertyValue:enctryptPropertyValue) {
			if(propertyValue.equals(value)) {
				return true;
			}
		}
		return false;
	}
}
