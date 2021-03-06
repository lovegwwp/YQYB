package com.jyss.yqy.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler,Exception e) {
		logger.info("进入全局异常处理器....");
		//控制台打印
		//e.printStackTrace();
		//向日志文件中写入异常
		//logger.error("系统发生异常",e);
		//跳转错误页面
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("message", "系统发生异常，请稍后重试。");
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
