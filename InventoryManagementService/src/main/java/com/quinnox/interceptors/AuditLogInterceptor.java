package com.quinnox.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.quinnox.model.User;
import com.quinnox.model.Visitor;
import com.quinnox.service.VisitorService;
import com.quinnox.utils.HttpRequestResponseUtils;

@Component
public class AuditLogInterceptor implements HandlerInterceptor {

	@Autowired
	private VisitorService visitorService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String ip = HttpRequestResponseUtils.getClientIpAddress();
		final String url = HttpRequestResponseUtils.getRequestUrl();
		final String page = HttpRequestResponseUtils.getRequestUri();
		final String refererPage = HttpRequestResponseUtils.getRefererPage();
		final String queryString = HttpRequestResponseUtils.getPageQueryString();
		final String userAgent = HttpRequestResponseUtils.getUserAgent();
		final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
//		final LocalDateTime timestamp = LocalDateTime.now();
		
//		LocalDateTime

		Visitor visitor = new Visitor();
		User user = HttpRequestResponseUtils.getLoggedInUser();
		if (user != null) {
			visitor.setUser(user.getUsername());
			visitor.setRole(user.getRole());
		}
		visitor.setIp(ip);
		visitor.setMethod(requestMethod);
		visitor.setUrl(url);
		visitor.setPage(page);
		visitor.setQueryString(queryString);
		visitor.setRefererPage(refererPage);
		visitor.setUserAgent(userAgent);
		visitor.setLoggedTime(System.currentTimeMillis());
//		visitor.setUniqueVisit(true);

		visitorService.saveVisitorInfo(visitor);

		return true;
	}

}
