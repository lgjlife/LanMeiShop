package com.lanmei.os.controller.kaptcha;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping
public class KaptchaImageCreate {
	
	private final static Logger logger = LoggerFactory.getLogger("KaptchaImageCreate.class");	
	{
		logger.debug("KaptchaImageCreate Create Bean............. ");
	}
	
	
	private Producer kaptchaProducer=null;  
	  
    @Autowired  
    public void setCaptchaProducer(Producer kaptchaProducer) {  
        this.kaptchaProducer = kaptchaProducer;  
    }  
  
    @RequestMapping("/kaptcha")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{  
    	
    	logger.debug("into kaptcha/image/create");
       response.setDateHeader("Expires",0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String verificationCode = kaptchaProducer.createText();  
        logger.debug("验证码 = " +  verificationCode);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, verificationCode); 
        
        Subject currentUser = SecurityUtils.getSubject();		
		Session session = currentUser.getSession();
		session.setAttribute("verificationCode",verificationCode);
		
        BufferedImage bi = kaptchaProducer.createImage(verificationCode);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  
}
