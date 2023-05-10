package com.team4.skillmarket.admin.login.controller;

import java.io.IOException;
import java.sql.ResultSet;

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
		
		try {
			System.out.println(req.getContextPath());
			String adminId = req.getParameter("adminId");
			String adminPwd = req.getParameter("adminPwd");
			
			AdminLoginVo avo = new AdminLoginVo();
			avo.setAdminId(adminId);
			avo.setAdminPwd(adminPwd);
			
			AdminLoginService as = new AdminLoginService();
			ResultSet rs = as.login(avo);
			
			if (rs != null) {
				req.getSession().setAttribute("AdminLoginVo", avo);
				resp.sendRedirect(req.getContextPath() + "/admin/home");
			}
			else {
				System.out.println("eerroorr");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
}
