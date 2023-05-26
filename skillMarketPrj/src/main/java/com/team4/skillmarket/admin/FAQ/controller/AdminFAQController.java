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
		
		try {
			
			
			
			AdminFAQService ads = new AdminFAQService();
			AdminFAQVo FAQHit = new AdminFAQVo();
			List<AdminFAQVo> FAQArrList =  ads.selectFAQList();
			FAQHit =  ads.gerHitFAQ();
			
			if (FAQArrList == null) {
				throw new Exception();
			}
			
			
			req.setAttribute("FAQHit", FAQHit);
			req.setAttribute("FAQArrList", FAQArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/FAQ.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}