package com.team4.skillmarket.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.member.service.MemberService;

@WebServlet("/join/check-id")
public class MemberCheckIdController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memberId = req.getParameter("memberId");
			
			MemberService ms = new MemberService();
			int result = ms.checkId(memberId);
			
			PrintWriter out = resp.getWriter();
			
			if(result == 0) {
				System.out.println("이미 사용한 아이디");
			}else {
				System.out.println("사용 가능한 아이디");
			}
			
			out.write(result + "");
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "아이디 중복 검사 중 에러 발생...");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
	
		
	}

}
