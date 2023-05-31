package com.team4.skillmarket.find.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/find/search-pwd")
public class PwdShowController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/find/pwdcomplete.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memberId = req.getParameter("memberId");
			String memberEmail = req.getParameter("memberEmail");
			
			MemberVo mvo = new MemberVo();
			mvo.setMemberId(memberId);
			mvo.setMemberEmail(memberEmail);
			
			MemberService ms = new MemberService();
			String pwd = ms.getPwdByIdEmail(mvo);
			
			if(pwd != null) {
				req.setAttribute("memberPwd", pwd);
				req.getRequestDispatcher("/WEB-INF/views/find/pwdcomplete.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("비밀번호 찾기 오류");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "비밀번호 찾기 오류");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
			
		}
		
	}
	
}
