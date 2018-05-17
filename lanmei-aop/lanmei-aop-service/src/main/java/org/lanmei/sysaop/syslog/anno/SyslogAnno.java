package org.lanmei.sysaop.syslog.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SyslogAnno {
	
	/** 位于什么层**/    
    public String layer() default "";    
	/** 方法描述**/    
    public String description() default "";    

}
