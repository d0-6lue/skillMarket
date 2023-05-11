package com.team4.skillmarket.admin.home.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/home")
public class AdminHomeController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/admin/home/home.jsp").forward(req, resp);
	}
	
}
