package com.team4.skillmarket.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.admin.login.vo.AdminVo;

@WebFilter("/admin/*")
public class AdminChecker implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	try {
			
    		 System.out.println("어드민 체크 ...");

    	        HttpServletRequest req = (HttpServletRequest) request;
    	        HttpServletResponse resp = (HttpServletResponse) response;

    	        // 관리자 체크
    	        HttpSession session = req.getSession();
    	        AdminVo admin = (AdminVo) session.getAttribute("AdminLogin");
    	        

    	        // 예외 처리
    	        String reqURI = req.getRequestURI();
    	        String path = req.getContextPath() + "/admin/login";

    	        if (reqURI.equals(path)) {
    	            chain.doFilter(request, response);
    	            return;
    	        }

    	        if (admin == null) {
    	            resp.sendRedirect(path);
    	            return;
    	        }

    	        chain.doFilter(request, response);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
       
    }
}
