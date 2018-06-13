package org.lanmei.cms.common.jdbc.encrypt;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ConvertPwdPropertyConfigurer extends PropertyPlaceholderConfigurer {

	/*@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		System.out.println("propertyName1　＝　" + propertyName + "  propertyValue1 = " + propertyValue);
		propertyName = propertyName + "_new";
		propertyValue = "_new";
		
		return super.convertProperty(propertyName, propertyValue);
	}*/
	
	 @Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
	        super.processProperties(beanFactoryToProcess, props);
	        HashMap  ctxPropMap = new HashMap<>();
	        for (Object key : props.keySet()){
	            String keyStr = key.toString();
	            String value = String.valueOf(props.get(keyStr));
	            
	            System.out.println("keyStr　＝　" + keyStr + "  value = " + value);
	            
	            props.setProperty(keyStr, value + "new");
	            System.setProperty(keyStr, value + "new"); 
	        }
	      /* Enumeration<?> keys = props.propertyNames();  
	        while (keys.hasMoreElements()) {  
	            String key = (String)keys.nextElement();  
	            String value = props.getProperty(key);  
	                props.remove(key);  
	                key = key.substring(0, key.length() - 11);  
	                props.setProperty(key, value);  
	            System.setProperty(key, value);  
	        }  */
	       
	        super.processProperties(beanFactoryToProcess, props);  
	    }
	 
	 @Override
		protected String convertProperty(String propertyName, String propertyValue) {
			// TODO Auto-generated method stub
			System.out.println("propertyName1　＝　" + propertyName + "  propertyValue1 = " + propertyValue);
			propertyName = propertyName + "_new";
			propertyValue = "_new";
			
			return super.convertProperty(propertyName, propertyValue);
		}
	
}

class ConvertPwdPropertyConfigurer1 extends PropertyPlaceholderConfigurer {

	/* @Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
	        super.processProperties(beanFactoryToProcess, props);
	        HashMap  ctxPropMap = new HashMap<>();
	        for (Object key : props.keySet()){
	            String keyStr = key.toString();
	            String value = String.valueOf(props.get(keyStr));
	            
	            System.out.println("keyStr　＝　" + keyStr + "  value = " + value);
	            
	            ctxPropMap.put(keyStr,value+"new");
	        }
	    }*/
	/*@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		System.out.println("propertyName　＝　" + propertyName + "  propertyValue = " + propertyValue);
		propertyValue = "_new";
		return super.convertProperty(propertyName, propertyValue);
	}*/
	
}

