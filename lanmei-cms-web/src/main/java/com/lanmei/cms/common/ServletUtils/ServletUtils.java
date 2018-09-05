package com.lanmei.cms.common.ServletUtils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ServletUtils {
	
	/**
	 * 获取ip,当x-forwarded-for为null时，表示请求没有经过代理，此时调用getRemoteAddr（）和getRemoteHost（）
	 * 都可获取真实ip,反之，则getHeader（"x-forwarded-for"）为真实的ip
	 * @return
	 */
	public static  String getAddrIP(HttpServletRequest request) {
		if(request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
		
	}
	public static String getAggent(HttpServletRequest request) {
		
		return request.getHeader("User-Agent");
	}
	 
}
