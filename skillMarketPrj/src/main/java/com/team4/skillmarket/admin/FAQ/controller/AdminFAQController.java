package com.team4.skillmarket.admin.FAQ.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.FAQ.service.AdminFAQService;
import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;

@WebServlet("/admin/FAQ")
public class AdminFAQController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		try {
			
			AdminFAQService ads = new AdminFAQService();
			List<AdminFAQVo> FAQArrList =  ads.selectFAQList();
			
			if (FAQArrList == null) {
				throw new Exception();
			}
			
			req.setAttribute("FAQArrList", FAQArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/FAQ.jsp").forward(req, resp);
			
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