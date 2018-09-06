package com.lanmei.sysaop.syslog.aspect;

import com.lanmei.sysaop.syslog.anno.PrintUrlAnno;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/** 
 * @description:  Controller 层打印请求路径切面类
 *                1.spring-mvc 配置文件添加切面注解支持 <aop:aspectj-autoproxy proxy-target-class="true"/>
 *                2.在Controller上添加注解@PrintUrlAnno
 * @param:
 * @return:
 * @author: Mr.lgj 
 * @date: 9/7/18 
*/ 
@Aspect
@Component
public class PrintUrlAspect {

	private final static Logger logger = LoggerFactory.getLogger("SysLogAspect.class");		
	{
		
		logger.debug("PrintUrlAspect create bean --------------");
	}

	/**
	 * 定义切点
	 * execution语法：
	 * （<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>）
	 * 1.通过方法	签名定义切点： （public * *(..)）;匹配所有目标类的public 方法
	 * 2.通过类定义切点 ： （* com.lanmei.user+.*(..)）:匹配user接口的所有方法，有+表示也匹配其 实现类
	 * 3.通过类包定义切点： （* com.lanmei.user.*(..)）匹配com.lanmei.user包下的所有类的所有方法
	 * 					 （* com.lanmei.user..*(..)）匹配com.lanmei.user包/子孙包下的所有类的所有方法
	 * 4.通过方法入参定义切点 * admin(String. *)）匹配方法admin的地一个参数为String ,第二个参数为任意类型的方法
	 */
	/*匹配 com.lanmei.os.controller本包及其子孙包的所有方法*/
//	@Pointcut(value = "execution(* com.lanmei.*.controller..*(..))")
	@Pointcut(value = "@annotation(com.lanmei.sysaop.syslog.anno.PrintUrlAnno)")
	public void urlLogPointcut() {
		
	}
	/**
	 * 前置通知
	 */
	@Before(value="urlLogPointcut()")
	public void doBefore(JoinPoint joinPoint) {

		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {

			
			String targetName = joinPoint.getTarget().getClass().getName();  			 
            String methodName = joinPoint.getSignature().getName();
            Class targetClass = Class.forName(targetName);
            Method method = targetClass.getMethod(methodName);
            //获取SyslogAnno注解值
            String description  = method.getAnnotation(PrintUrlAnno.class).description();


			//打印请求路径
             logger.debug("\r\n 访问  " + joinPoint.getTarget().getClass().getName() //类名称
            		+  "  method = " + methodName
            		+  "  路径 = "
				    +  request.getRequestURI()
             		+ "  描述：" + description);//类功能描述


		} catch (Exception e) {
			logger.debug("前置通知出现异常,error = " + e);
		}
		
		
	}



}
