package com.team4.skillmarket.admin.category.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.category.service.AdminCatagoryService;
import com.team4.skillmarket.admin.category.vo.CategoryEditVo;

@WebServlet("/admin/category/edit")
public class AdminCategoryEditController extends HttpServlet{
	
	private final AdminCatagoryService cs = new  AdminCatagoryService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String big = req.getParameter("big");
		String meddle = req.getParameter("meddle");
		String small = req.getParameter("small");
		String name = req.getParameter("name");
		
		CategoryEditVo vo = new CategoryEditVo();
		vo.setBig(big);
		vo.setMeddle(meddle);
		vo.setName(name);
		
		int result =  cs.addCat(vo);
		
		
		
		
	}
	
}
