package com.lanmei.sysaop.syslog.anno;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintUrlAnno {
	
	/** 位于什么层**/    
    public String layer() default "";    
	/** 方法描述**/    
    public String description() default "";    

}
