package com.picverse.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.picverse.util.CookieUtil;
import com.picverse.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String ROOT = "/";
	private static final String CREATE = "/create";
	private static final String DELETE = "/delete";
	private static final String VIEW = "/view";
	private static final String UPDATE = "/edit";
	private static final String PROFILE = "/profile";
	private static final String DELETE_COMMENT = "/delete-comment";
	private static final String EDIT_PROFILE = "/edit-profile";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization logic, if required
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		// Only check authentication for specific paths
		boolean isProtectedPath = uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME)
				|| uri.endsWith(ROOT) || uri.endsWith(CREATE) || uri.endsWith(DELETE) || uri.endsWith(UPDATE)
				|| uri.endsWith(PROFILE) || uri.endsWith(DELETE_COMMENT) || uri.endsWith(VIEW) || uri.endsWith(EDIT_PROFILE);

		if (!isProtectedPath) {
			chain.doFilter(request, response); // Let all others pass
			return;
		}

		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue()
				: null;

		if ("admin".equals(userRole)) {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(contextPath + HOME);
			} else {
				chain.doFilter(request, response);
			}
		} else if ("user".equals(userRole)) {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(contextPath + HOME);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(contextPath + LOGIN);
			}
		}
	}

	@Override
	public void destroy() {
		// Cleanup logic, if required
	}
}
