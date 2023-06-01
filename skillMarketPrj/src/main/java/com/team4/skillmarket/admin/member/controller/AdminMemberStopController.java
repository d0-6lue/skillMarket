package com.team4.skillmarket.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.CacheHttpServlet;
import com.team4.skillmarket.admin.member.service.AdminMemberService;

@WebServlet("/admin/members/stop")
public class AdminMemberStopController extends CacheHttpServlet{
	
	private final AdminMemberService ms = new AdminMemberService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String memberNo = req.getParameter("memberNo");
			
			AdminMemberService ms = new AdminMemberService();
			 
			int stopMember =  ms.stopMember(memberNo);
			
			if (stopMember != 1) {
				resp.setCharacterEncoding("UTF-8");
		        resp.getWriter().write("error");
			}
			
			resp.setCharacterEncoding("UTF-8");
	        resp.getWriter().write("ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		
		
		
	}
	
}
