package com.example.cubtest.aop;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

@Component
@Aspect
public class LoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpServletRequest httpRequest;

	// 設定切入點
	@Pointcut("execution(* com.example.cubtest.controller..*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {

		HttpSession session = httpRequest.getSession();

		session.getAttribute("member");

		StringBuilder logStr = new StringBuilder();

		logStr.append("訪問：").append(joinPoint.getSignature().getDeclaringTypeName()).append(" - ")
				.append(joinPoint.getSignature().getName());
		logStr.append("｜Method：").append(httpRequest.getMethod());

		String contentType = httpRequest.getContentType();

		logStr.append("｜Content-Type：").append(contentType);
		
		if (contentType != null) {

			if (contentType.contains("application/json")) {

				logStr.append("｜Json Body：");
				
				logStr.append(this.getJsonBody());

			} else {

				logStr.append("｜Parameter：");

				logStr.append(this.getParameterStr());
			}
		} else {
			logStr.append("｜Parameter：");

			logStr.append(this.getUrlParameterStr());
		}

		logger.info(logStr.toString());
	}

	private String getParameterStr() {

		StringBuilder logStr = new StringBuilder();

		List<String> names = Collections.list(httpRequest.getParameterNames());

		if (names != null) {
			names.forEach((key) -> {
				if (names.indexOf(key) != 0) {
					logStr.append("，");
				}
				logStr.append(key).append("=").append(httpRequest.getParameter(key));
			});
		}

		return logStr.toString();
	}

	private String getJsonBody() {

		StringBuilder logStr = new StringBuilder();
		
		try (BufferedReader reader = httpRequest.getReader()) {

			String line = null;

			while ((line = reader.readLine()) != null) {
				logStr.append("\r\n");
				logStr.append(line);
			}

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		return logStr.toString();
	}
	
	@SuppressWarnings("unchecked")
	private String getUrlParameterStr() {

		StringBuilder logStr = new StringBuilder();

		Map<String, String> map = (Map<String, String>) httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		int i = 0;
		
		if (map != null) {
			
			for (String key : map.keySet()) {
				if (i != 0) {
					logStr.append("，");
				}
				logStr.append(key).append("=").append(map.get(key));
				i++;
			}
		}

		return logStr.toString();
	}
}
