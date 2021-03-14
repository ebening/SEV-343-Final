package com.adinfi.seven.presentation.views.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adinfi.seven.persistence.dto.UsuarioDTO;

public class SessionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getRequestURI();
		if (path.startsWith(request.getContextPath() + "/faces/pages/admin")
				|| path.startsWith(request.getContextPath()
						+ "/faces/javax.faces.resource")
				|| path.startsWith(request.getContextPath()
						+ "/faces/resources")) {
			chain.doFilter(req, res);
		} else {
			try {
				UsuarioDTO userLogin = (UsuarioDTO) request.getSession()
						.getAttribute(Constants.LOGIN_VAR);
				if (userLogin == null) {
					response.sendRedirect(request.getContextPath()
							+ "/faces/pages/admin/Login.xhtml");
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
