package com.team4.skillmarket.admin.FAQ.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.CacheHttpServlet;
import com.team4.skillmarket.admin.FAQ.service.AdminFAQService;
import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.home.service.HomeService;

@MultipartConfig
@WebServlet("/admin/FAQ/edit")
public class AdminFAQEditController extends CacheHttpServlet {
	
	private final AdminFAQService fs = new  AdminFAQService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String no = req.getParameter("faqNo");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String category = req.getParameter("category");
			
			
			AdminFAQVo vo = new AdminFAQVo();
			vo.setFaqNo(no);
			vo.setFaqAContent(content);
			vo.setFaqQContent(title);
			vo.setFaqCatNo(category);
		
			int result =  fs.editFAQ(vo);
			
			if (result != 1) {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("error");
				throw new IllegalStateException("error");
			}
			
			
			AdminFAQVo updateFAQList =  fs.updateFAQList(vo);
			System.out.println(updateFAQList);
			if (updateFAQList == null) {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("error");
			}
			
	        Gson gson = new Gson();
	        String update = gson.toJson(updateFAQList);
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        resp.getWriter().write(update);
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
