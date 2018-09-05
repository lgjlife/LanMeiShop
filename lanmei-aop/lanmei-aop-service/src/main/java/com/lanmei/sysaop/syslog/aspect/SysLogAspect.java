package com.lanmei.sysaop.syslog.aspect;

import com.lanmei.aop.dao.model.SystemLog;
import com.lanmei.sysaop.syslog.anno.SyslogAnno;
import com.lanmei.sysaop.syslog.service.SysLogService;
import com.lanmei.user.dao.model.OsUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;


@Aspect
@Component
@Order(1)
public class SysLogAspect {

	private final static Logger logger = LoggerFactory.getLogger("SysLogAspect.class");		
	{
		
		logger.debug("SysLogAspect create bean --------------");
	}
	@Autowired
	SysLogService sysLogService;
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
	@Pointcut(value = "@annotation(com.lanmei.sysaop.syslog.anno.SyslogAnno)")
	public void sysLogPointcut() {
		
	}
	/**
	 * 前置通知
	 */
	@Before(value="sysLogPointcut()")
	public void doBefore(JoinPoint joinPoint) {
		logger.debug("SysLogAspect 进入before切面  ");
		
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//获取当前登录的用户
		OsUser user = null;//(OsUser)SessionUtils.getSession("currenLogintUser");
		if(user == null) {
			user = new OsUser();
			user.setPhoneNum("0");
			logger.debug("非登陆用户");
		}
		logger.debug("检测参数0");
        String params = "";
        logger.debug("检测参数１");
        if(joinPoint.getArgs() == null)
        {
        	logger.debug("getArgs is null");
        	
        }
        else {
        	logger.debug("length = " + joinPoint.getArgs().length);
        }
        logger.debug("getArgs is not null");
        
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
          
            params=Arrays.toString(joinPoint.getArgs());  
       } 
        logger.debug("params　＝　" + params);
		//请求IP
		String ip = request.getRemoteAddr();  
		try {
			String methodRequestPath = "/";
			String classRequestPath = "/";
			
			
			String targetName = joinPoint.getTarget().getClass().getName();  			 
            String methodName = joinPoint.getSignature().getName();               
            Object[] arguments = joinPoint.getArgs();               
            Class targetClass = Class.forName(targetName);    
             
            Method method = targetClass.getMethod(methodName);            
            //获取SyslogAnno注解值
            String description = description = method.getAnnotation(SyslogAnno.class).description();
            String layer  = method.getAnnotation(SyslogAnno.class).layer(); 
   
           //获取方法注解RequestMapping中请求路径
            String[] methodRequestPathArr = method.getAnnotation(RequestMapping.class).path(); 
            if(methodRequestPathArr.length > 0) {
            	methodRequestPath = methodRequestPathArr[0]; 
            }           
             //获取类注解RequestMapping中请求路径
             RequestMapping requestMapping = (RequestMapping)targetClass.getAnnotation(RequestMapping.class);
          
             String[] classRequestPathArr = requestMapping.path();
          
             if(classRequestPathArr .length > 0) {
            	 classRequestPath = classRequestPathArr[0]; 
             }  
 
             //打印请求路径
             logger.debug("\r\n into  " + joinPoint.getTarget().getClass().getName() //类名称
            		+  "  method = " + methodName
            		+  "  path = "
             		+  classRequestPath //Controller类的请求路径
             		+  methodRequestPath //Controller类的方法的请求路径
             		+ "  " + description);//类功能描述
             
            //准备数据库数据
            SystemLog sysLog =  new SystemLog();
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
     		sysLog.setLayer(layer);
     		//写入数据库
     		//sysLogService.saveLog(sysLog);

		} catch (Exception e) {
			logger.debug("前置通知出现异常,error = " + e);
		}
		
		
	}
	/**
	 * 后置通知
	 */
	@After(value="sysLogPointcut()")
	public void doAfter(JoinPoint joinPoint) {

		logger.debug("SysLogAspect 进入after切面  ");
	}

	/**
	 * 后置增强通知
	 */
	@AfterThrowing(pointcut="sysLogPointcut()",throwing="ex")
	public void doAfterThrowing(JoinPoint joinPoint ,Throwable ex ) {
		System.out.println("SysLogAspect 切面 - AfterThrowing");
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//获取当前登录的用户
		OsUser user = null;//(OsUser)SessionUtils.getSession("currenLogintUser");
		if(user == null) {
			user = new OsUser();
			user.setPhoneNum("非登陆用户");
			logger.debug("非登陆用户");
		}
		logger.debug("检测参数0");
        String params = "";
        logger.debug("检测参数１");
        if(joinPoint.getArgs() == null)
        {
        	logger.debug("getArgs is null");
        	
        }
        else {
        	logger.debug("length = " + joinPoint.getArgs().length);
        }
        logger.debug("getArgs is not null");
        
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
          
            params=Arrays.toString(joinPoint.getArgs());  
       } 
        logger.debug("params　＝　" + params);
		//请求IP
		String ip = request.getRemoteAddr();  
		try {
			String methodRequestPath = "/";
			String classRequestPath = "/";
			
			String targetName = joinPoint.getTarget().getClass().getName();    
            String methodName = joinPoint.getSignature().getName();    
            Object[] arguments = joinPoint.getArgs();    
            Class targetClass = Class.forName(targetName);    
          
            logger.debug("methodName　＝　" + methodName);
            logger.debug("targetClass　＝　" + targetClass);
            Method method = targetClass.getMethod(methodName); 
            logger.debug("method　＝　" + method);
            String description = method.getAnnotation(SyslogAnno.class).description();  
            logger.debug("description　＝　" + description);
            String layer  = method.getAnnotation(SyslogAnno.class).layer(); 
            logger.debug("layer = " + layer);
           //获取方法注解RequestMapping中请求路径
            String[] methodRequestPathArr = method.getAnnotation(RequestMapping.class).path();
            if(methodRequestPathArr.length > 0) {
            	methodRequestPath = methodRequestPathArr[0]; 
            }  
             //获取类注解RequestMapping中请求路径
             RequestMapping requestMapping = (RequestMapping)targetClass.getAnnotation(RequestMapping.class);
             String[] classRequestPathArr = requestMapping.path();
             if(classRequestPathArr.length > 0) {
            	 classRequestPath = classRequestPathArr[0]; 
             }  
             
             //打印请求路径
             logger.debug("into  " + joinPoint.getTarget().getClass().getName() //类名称
            		+  "\r\n  method = " + methodName
            		+  "  path = "
             		+  classRequestPath //Controller类的请求路径
             		+  methodRequestPath //Controller类的方法的请求路径
             		+ "  " + description);//类功能描述
             
            SystemLog sysLog =  new SystemLog();
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
     		sysLog.setLayer(layer);
     		//写入数据库
     		sysLogService.saveLog(sysLog);

		} catch (Exception e) {
			logger.debug("SysLogAspect　捕获异常,error = " + e);
		}
	}

}
