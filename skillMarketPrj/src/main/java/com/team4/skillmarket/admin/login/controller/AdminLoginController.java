package com.team4.skillmarket.admin.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.login.service.AdminLoginService;
import com.team4.skillmarket.admin.login.vo.AdminLoginVo;

@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/admin/home/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(123);
		String adminId = req.getParameter("adminId");
		String adminPwd = req.getParameter("adminPw");
		
		AdminLoginVo avo = new AdminLoginVo();
		avo.setAdminId(adminId);
		avo.setAdminPwd(adminPwd);
		
		AdminLoginService as = new AdminLoginService();
		as.login(avo);
		
		
		
		
		
	}
	
}
