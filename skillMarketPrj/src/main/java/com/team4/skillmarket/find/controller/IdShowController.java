package com.team4.skillmarket.find.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.member.service.MemberService;

@WebServlet("/find/search-id")
public class IdShowController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/find/idcomplete.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memberEmail = req.getParameter("memberEmail");
			
			MemberService ms = new MemberService();
			String id = ms.getIdByEmail(memberEmail);
			
			if(id != null) {
				req.setAttribute("memberId", id);
				req.getRequestDispatcher("/WEB-INF/views/find/idcomplete.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("아이디 찾기 오류");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "아이디 찾기 오류");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
			
		}
		
	}
	
}
