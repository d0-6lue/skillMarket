package com.team4.skillmarket.admin.category.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team4.skillmarket.admin.category.service.AdminCatagoryService;
import com.team4.skillmarket.admin.category.vo.CategoryEditVo;

@WebServlet("/admin/category/edit")
public class AdminCategoryEditController extends HttpServlet{
	
	private final AdminCatagoryService cs = new  AdminCatagoryService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String big = req.getParameter("big");
			String meddle = req.getParameter("meddle");
			String small = req.getParameter("small");
			String name = req.getParameter("name");
			String check = req.getParameter("BMScheck");
			String scope ="";
			
			System.out.println(big);
			System.out.println(meddle);
			System.out.println(small);
			System.out.println(check);
			
			CategoryEditVo vo = new CategoryEditVo();
			
			
			if ("big".equals(check) ) {
				scope = "1";
			}
			else if ("meddle".equals(check)  ) {
				scope = "2 ";
			}
			else if("small".equals(check) ) {
				scope = "3";
			}
			else {
				
		        resp.setCharacterEncoding("UTF-8");
		        resp.getWriter().write("error");
		        throw new IllegalStateException("카테고리 추가 실패");
			}
			
			
			vo.setBig(big);
			vo.setMeddle(meddle);
			vo.setName(name);
			vo.setScope(scope);
			vo.setCheck(check);
			
			int result =  cs.addCat(vo);
			
			if (result != 1) {
				resp.setCharacterEncoding("UTF-8");
		        resp.getWriter().write("error");
		        throw new IllegalStateException("카테고리 추가 실패");
			}
			
			resp.setCharacterEncoding("UTF-8");
	        resp.getWriter().write("ok");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
