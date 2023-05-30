package com.team4.skillmarket.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/login-check")
public class MemberLoginCheckController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			
			MemberVo mvo = new MemberVo();
			mvo.setMemberId(memberId);
			mvo.setMemberPwd(memberPwd);
			
			MemberService ms = new MemberService();
			int result = ms.checkLogin(mvo);
			
			PrintWriter out = resp.getWriter();
			
			System.out.println(result);
			
			out.write(result + "");
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "로그인 중 에러 발생...");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
	}
	
}
