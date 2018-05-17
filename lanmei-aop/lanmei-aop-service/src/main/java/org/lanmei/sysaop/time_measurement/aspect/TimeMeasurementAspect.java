package org.lanmei.sysaop.time_measurement.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lanmei.sysaop.dao.time_measurement.model.TimeMeasurement;
import org.lanmei.sysaop.time_measurement.anno.TimeMeasurementAnno;
import org.lanmei.sysaop.time_measurement.service.TimeMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(10)
@Aspect
@Component
public class TimeMeasurementAspect {

	private final static Logger logger = LoggerFactory.getLogger("SysLogAspect.class");		
	{
		logger.debug("SysLogAspect beaan create....");
		
	}
	@Autowired
	TimeMeasurementService timeMeasurementService;
	/**
	 * 定义切点
	 * execution语法：
	 * （<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>）
	 * 1.通过方法	签名定义切点： （public * *(..)）;匹配所有目标类的public 方法
	 * 2.通过类定义切点 ： （* org.lanmei.user+.*(..)）:匹配user接口的所有方法，有+表示也匹配其 实现类
	 * 3.通过类包定义切点： （* org.lanmei.user.*(..)）匹配org.lanmei.user包下的所有类的所有方法
	 * 					 （* org.lanmei.user..*(..)）匹配org.lanmei.user包/子孙包下的所有类的所有方法
	 * 4.通过方法入参定义切点 * admin(String. *)）匹配方法admin的地一个参数为String ,第二个参数为任意类型的方法
	 */

	/**
	 * 匹配注解TimeSpendAnno
	 */
	@Pointcut(value = "@annotation(org.lanmei.sysaop.time_measurement.anno.TimeMeasurementAnno)")
	public void annotationPointcut() {
		
	}	
	@Around(value = "annotationPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws  Throwable{

        System.out.println("TimeMeasurementAspect 进入环绕通知");
        Date date = new Date();
        Long start = date.getTime();
        //执行方法,获取返回值
        Object result =  pjp.proceed();       
        Date date1 = new Date();
        Long end = date1.getTime();
        Long runtime = end - start;
     
        
        String targetName = pjp.getTarget().getClass().getName(); 
   
        String methodName = pjp.getSignature().getName();

        Class targetClass = Class.forName(targetName);    
   
        Method method = targetClass.getMethod(methodName);
        //获取注解
        String layer =  method.getAnnotation(TimeMeasurementAnno.class).layer();
        String desc =  method.getAnnotation(TimeMeasurementAnno.class).description();
        
        
        TimeMeasurement timeMeasurement = new TimeMeasurement(getMethod(targetName,methodName), 
        		desc, layer, runtime, 1L, runtime);
        timeMeasurementService.saveTimeMeasurement(timeMeasurement);
        System.out.println("method = " + targetClass + methodName
        		+ "  layer =  " + layer
        		+ " desc =  " + desc);
        logger.debug("TimeMeasurementAspect 退出环绕通知");        
        return result;
    }
	private String getMethod(String classIn , String methodIn) {
		
		return (classIn + "." + methodIn);
	}
}

