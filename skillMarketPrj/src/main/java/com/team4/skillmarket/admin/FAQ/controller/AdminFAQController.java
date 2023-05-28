package com.team4.skillmarket.admin.FAQ.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.FAQ.service.AdminFAQService;
import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.admin.home.service.HomeService;

@WebServlet("/admin/FAQ")
public class AdminFAQController extends HttpServlet{
	
	private final HomeService hs = new  HomeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			
			
			AdminFAQService ads = new AdminFAQService();
			AdminFAQVo FAQHit = new AdminFAQVo();
			List<AdminFAQVo> FAQArrList =  ads.selectFAQList();
			FAQHit =  ads.gerHitFAQ();
			Map<String, List<?>> catNameMap = hs.getCategoryNameByHome();
			
			if (FAQArrList == null) {
				throw new IllegalStateException("홈화면의 모든 정보가 null 확인 필요");
			}
			if (catNameMap == null) {
				throw new IllegalStateException("홈화면의 통계 리스트가 null 확인 필요");
			}
			
			
			req.setAttribute("catNameMap", catNameMap);
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