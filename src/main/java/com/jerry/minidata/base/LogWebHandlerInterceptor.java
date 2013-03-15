package com.jerry.minidata.base;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截Web请求
 * 记录日志
 * 
 * @author Qianyong, Deng
 * @created Sep 17, 2012
 *
 */
@SuppressWarnings("unchecked")
public class LogWebHandlerInterceptor implements HandlerInterceptor {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(LogWebHandlerInterceptor.class);
	
	private static final String EXCLUDE_KEY_STRING = "exclude.atrribuite.name";
	
	private static final String UUID_MDC_KEY = "UUID";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		// 将UUID存入Log4J的MDC中
		MDC.put(UUID_MDC_KEY, RequestUUIDContextHolder.get());
		
		logger.info("***** {} ***** Handle request for URI ({}).",
				RequestUUIDContextHolder.get(), request.getRequestURI());
		
		StringBuilder sb = new StringBuilder();
		for (Iterator<Entry<String, Object>> iter = request.getParameterMap().entrySet().iterator(); iter.hasNext();) {
			Entry<String, Object> entry = iter.next();
			write(sb, entry.getKey(), entry.getValue());
		}
		logger.info("***** {} ***** Parameters of request : ({}). ",
				RequestUUIDContextHolder.get(), sb.toString());
		
		sb = new StringBuilder();
		for (Enumeration<String> en = request.getAttributeNames(); en.hasMoreElements();) {
			String key = en.nextElement();
//			if (isExclude(key)) continue; // 有非用户字段跳过
			write(sb, key, request.getAttribute(key));
		}
		logger.info("***** {} ***** Attributes of request : ({}). ",
				RequestUUIDContextHolder.get(), sb.toString());
		
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		logger.info("***** {} ***** Handle request finished. ", RequestUUIDContextHolder.get());
		
		// 删除MDC中的UUID
		MDC.remove(UUID_MDC_KEY);
		
		// 页面渲染完成后删除请求上下文的UUID
		RequestUUIDContextHolder.remove();
	}
	
	// 文本输出
	private void write(StringBuilder sb, String key, Object value) {
		sb.append("[");
		sb.append(key);
		sb.append(" : ");
		sb.append(isArray(value) ? writeArray(value) : value);
		sb.append("]");
	}
	
	// 将数组的各个元素值输出
	private String writeArray(Object value)	{
		StringBuilder sb = new StringBuilder();
		int length = Array.getLength(value);
		for (int pos = 0; pos < length; pos++) {
			sb.append(Array.get(value, pos));
			sb.append(pos == length -1 ? "" : ",");
		}
		return sb.toString();
	}
	
	// 判断是否是数组
	private boolean isArray(Object value) {
		return value != null && value.getClass().isArray();
	}

	// 判断是否是应该排除的字段
	private boolean isExclude(String name) {
		String values = PropertiesTool.getEnvironmentValue(EXCLUDE_KEY_STRING);
		if (StringUtils.isEmpty(values)) return false;
		
		for (String key : StringUtils.split(values, ",")) {
			if (StringUtils.contains(name, key)) return true;
		}
		
		return false;
	}
}
