package com.team4.skillmarket.common.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		} catch (Exception e) {
			System.out.println("[ERROR] filter error ...");
			e.printStackTrace();
			try {
				request.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}






