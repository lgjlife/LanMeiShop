package org.lanmei.common.syslog.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.lanmei.common.syslog.annotation.SyslogAnno;
import org.lanmei.common.syslog.model.SysLog;
import org.lanmei.common.syslog.service.SysLogService;
import org.lanmei.user.dao.model.OsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.reflect.ClassPath;


@Aspect
@Component
public class SysLogAspect {

	private final static Logger logger = LoggerFactory.getLogger("SysLogAspect.class");		
	@Autowired
	SysLogService sysLogService;
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
	/*匹配 org.lanmei.os.controller本包及其子孙包的所有方法*/
	@Pointcut(value = "execution(* org.lanmei.os.controller..*(..))")
	public void controllerPointcut() {
		
	}
	/**
	 * 前置通知
	 */
	@Before(value="controllerPointcut()")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("切面 - before");
		
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//获取当前登录的用户
		OsUser user = null;//(OsUser)SessionUtils.getSession("currenLogintUser");
		if(user == null) {
			user = new OsUser();
			user.setPhoneNum("0");
			logger.debug("非登陆用户");
		}
        String params = "";    
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
          
            params=Arrays.toString(joinPoint.getArgs());  
       } 
		//请求IP
		String ip = request.getRemoteAddr();  
		try {
			String methodRequestPath = null;
			String targetName = joinPoint.getTarget().getClass().getName();    
            String methodName = joinPoint.getSignature().getName();    
            Object[] arguments = joinPoint.getArgs();    
            Class targetClass = Class.forName(targetName);    
            Method[] methods = targetClass.getMethods();  
            String description = "";  
            String operationName = "";  
             for (Method method : methods) {    
                 if (method.getName().equals(methodName)) {    
                    Class[] clazzs = method.getParameterTypes();    
                     if (clazzs.length == arguments.length) {    
                    	 description = method.getAnnotation(SyslogAnno.class).description();  
                         operationName = method.getAnnotation(SyslogAnno.class).operationName(); 
                         //获取方法注解RequestMapping中请求路径
                         String[] path = method.getAnnotation(RequestMapping.class).path();
                         methodRequestPath = path[0]; 
                         
                         break;    
                    }    
                }    
            }  
             //获取类注解RequestMapping中请求路径
             RequestMapping requestMapping = (RequestMapping)targetClass.getAnnotation(RequestMapping.class);
             String[] classRequestPath = requestMapping.path();
             //打印请求路径
             logger.debug("into  " + joinPoint.getTarget().getClass().getName() //Controller类名称
            		+  "  path = "
             		+  classRequestPath[0] //Controller类的请求路径
             		+  methodRequestPath //Controller类的方法的请求路径
             		+ "  " + description);//类功能描述
             
            //准备数据库数据
            SysLog sysLog =  new SysLog();
     		sysLog.setMethod((joinPoint.getTarget().getClass().getName() 
			     				+ "." + joinPoint.getSignature().getName() + "()"));
     		sysLog.setLogtype((byte)(0));
     		sysLog.setRequestIp(ip);
     		sysLog.setExceptionCode(null);
     		sysLog.setExceptionDetail(null);
     		sysLog.setParams(params);
     		sysLog.setCreateBy(user.getPhoneNum());
     		sysLog.setCreateDate(new Date());
     		sysLog.setDescription(description);
     		//写入数据库
     		sysLogService.saveLog(sysLog);

		} catch (Exception e) {
			logger.debug("前置通知出现异常,error = " + e);
		}
		
		
	}
	/**
	 * 后置通知
	 */
	/*@After(value="controllerPointcut()")
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("切面 - after");
	}
	*/
	/**
	 * 后置增强通知
	 */
	@AfterThrowing(pointcut="controllerPointcut()",throwing="ex")
	public void doAfterThrowing(JoinPoint joinPoint ,Throwable ex ) {
		System.out.println("切面 - AfterThrowing");
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//获取当前登录的用户
		OsUser user = null;//(OsUser)SessionUtils.getSession("currenLogintUser");
		if(user == null) {
			user = new OsUser();
			user.setPhoneNum("非登陆用户");
			logger.debug("非登陆用户");
		}
        String params = "";    
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
          
            params=Arrays.toString(joinPoint.getArgs());  
       } 
		//请求IP
		String ip = request.getRemoteAddr();  
		try {
			String methodRequestPath = null;
			String description = "";  
	        String operationName = "";  
			String targetName = joinPoint.getTarget().getClass().getName();    
            String methodName = joinPoint.getSignature().getName();    
            Object[] arguments = joinPoint.getArgs();    
            Class targetClass = Class.forName(targetName);    
            Method[] methods = targetClass.getMethods();  
           
             for (Method method : methods) {    
                 if (method.getName().equals(methodName)) {    
                    Class[] clazzs = method.getParameterTypes();    
                     if (clazzs.length == arguments.length) {    
                    	 description = method.getAnnotation(SyslogAnno.class).description();  
                         operationName = method.getAnnotation(SyslogAnno.class).operationName();  
                         //获取方法注解RequestMapping中请求路径
                         String[] path = method.getAnnotation(RequestMapping.class).path();
                         methodRequestPath = path[0]; 
                         break;    
                    }    
                }    
            }  
           //获取类注解RequestMapping中请求路径
            RequestMapping requestMapping = (RequestMapping)targetClass.getAnnotation(RequestMapping.class);
            String[] classRequestPath = requestMapping.path();
           //写入数据库
            logger.debug("into  " + joinPoint.getTarget().getClass().getName() //Controller类名称
            		+  "  path = "
            		+  classRequestPath[0] //Controller类的请求路径
            		+  methodRequestPath //Controller类的方法的请求路径
            		+ "  " + description);//类功能描述
             
            SysLog sysLog =  new SysLog();
     		sysLog.setMethod((joinPoint.getTarget().getClass().getName() 
			     				+ "." + joinPoint.getSignature().getName() + "()"));
     		sysLog.setLogtype((byte)(1));
     		sysLog.setRequestIp(ip);
     		sysLog.setExceptionCode(ex.getClass().getName());
     		sysLog.setExceptionDetail("messsge = " + ex.getMessage() + "   cause = " + ex.getCause() );
     		sysLog.setParams(params);
     		sysLog.setCreateBy(user.getPhoneNum());
     		sysLog.setCreateDate(new Date());
     		sysLog.setDescription(description);
     		//写入数据库
     		sysLogService.saveLog(sysLog);

		} catch (Exception e) {
			logger.debug("前置通知出现异常,error = " + e);
		}
	}
	
	/*@Around(value = "controllerPointcut()")
    public void around(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("进入环绕通知");
        Date date = new Date();
        Long start = date.getTime();
        pjp.proceed();
        Date date1 = new Date();
        Long end = date1.getTime();
        Long runtime = end - start;
        System.out.println(" runtime  = " + runtime );
        System.out.println("退出环绕通知");
    }*/
}

