package com.team4.skillmarket.csc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.csc.service.CSCService;

@WebServlet("/faq/detail")
public class FAQDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String no = req.getParameter("no");
			
			CSCService cs = new CSCService();
			int result = cs.addFAQHit(no);
			
			if(result != 1) {
				throw new Exception("조회수 에러");
			}
			
			AdminFAQVo fvo = cs.getFAQByNo(no);
			
			req.setAttribute("fvo", fvo);
			req.getRequestDispatcher("/WEB-INF/views/csc/faqdetail.jsp").forward(req, resp);
			
		} catch (Exception e) {
		}
		
		
	}
	
}
