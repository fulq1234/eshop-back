package com.love.business.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ParameterAOP {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	//定义一个切面
	@Pointcut("execution (* com.love.business.controller..*(..))")
	private void cut() {
		
	}
	
	@Around("cut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        
		String ctx = request.getContextPath();//context path
		logger.debug("cut EOPAOP");
		request.getSession().setAttribute("ctx", ctx);
		Object object = pjp.proceed();
		return object;
	}
}
