package org.lanmei.common.syslog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SyslogAnno {
	/** 方法描述**/    
    public String description() default "";    
    /** 要执行的具体操作比如：添加用户 **/    
    public String operationName() default "";  
}
