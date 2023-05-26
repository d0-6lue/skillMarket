package com.team4.skillmarket.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.CacheHttpServlet;

@WebServlet("/admin/members/sort")
public class AdminMemberSortController extends CacheHttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.getParameter(getServletName());

	}
	
}
