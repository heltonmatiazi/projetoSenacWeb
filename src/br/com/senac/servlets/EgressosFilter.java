package br.com.senac.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/html/*")
public class EgressosFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		HttpSession session = request.getSession();
		if (session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
