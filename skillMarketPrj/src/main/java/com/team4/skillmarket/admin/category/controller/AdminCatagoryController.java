package com.team4.skillmarket.admin.category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.category.service.AdminCatagoryService;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.admin.login.vo.AdminLoginVo;

@WebServlet("/admin/category")
public class AdminCatagoryController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		try {
			
			AdminCatagoryService acs = new AdminCatagoryService();
			List<AdminCategoryVo> catArrList =  acs.selectCatList();
			
			if (catArrList == null) {
				throw new Exception();
			}
			
			req.setAttribute("catArrList", catArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/category.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}