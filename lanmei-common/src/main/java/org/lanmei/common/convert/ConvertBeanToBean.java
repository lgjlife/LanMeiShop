package org.lanmei.common.convert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 复制两个Ｂean之间的同名属性值
 * @author lgj
 *
 */
public class ConvertBeanToBean {

	/**
	 * @Desc 将from　Bean　的值赋给　to Bean　中的同名属性
	 * @param from  
	 * @param to
	 * @return
	 */
	public static Object convert(Object from ,Object to){
		try {
			 BeanInfo beanInfofrom = Introspector.getBeanInfo(from.getClass());   
			 PropertyDescriptor[] psFrom = beanInfofrom.getPropertyDescriptors();
			 
			 Method[] toMethods = to.getClass().getDeclaredMethods();
			 for(PropertyDescriptor p : psFrom){
				 //from
				 Method fromMethod = p.getReadMethod();
				 String fromMethodName = fromMethod.getName();			 				 
				 Method toWriteMethod =  findMethodByName(toMethods,"set" + fromMethodName.substring(3));
				 if(toWriteMethod == null) {
					 continue;
				 }
				 try {
					 
					 Object data = fromMethod.invoke(from);
					 if(data != from.getClass()) {
						 toWriteMethod.invoke(to, data);
					 }					 
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				 catch (Exception e) {
					 e.printStackTrace();
					 continue;   
				}	 
			 } 
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}  
		 catch (Exception e) {
			 e.printStackTrace();
		}  
        return to;   
	}
	
	public static Method findMethodByName(Method[] methods, String name) {

        for (int j = 0; j < methods.length; j++) {
            if (methods[j].getName().equals(name)) {
                return methods[j];
            }
        }
        return null;
    }
}
