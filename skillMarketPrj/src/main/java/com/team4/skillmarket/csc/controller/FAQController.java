package com.team4.skillmarket.csc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.FAQ.vo.FAQCategoryVo;
import com.team4.skillmarket.csc.service.CSCService;

@WebServlet("/faq")
public class FAQController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			CSCService cs = new CSCService();
			List<AdminFAQVo> faqList = cs.getFAQList();
			List<FAQCategoryVo> categoryList = cs.getFAQCategoryList();
			
			req.setAttribute("categoryList", categoryList);
			req.setAttribute("faqList", faqList);
			req.getRequestDispatcher("/WEB-INF/views/csc/faq.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("faq 조회 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "faq 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
		
	}
	
}
