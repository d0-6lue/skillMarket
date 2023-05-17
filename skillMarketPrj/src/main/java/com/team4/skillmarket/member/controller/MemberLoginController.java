package com.team4.skillmarket.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/login")
public class MemberLoginController extends HttpServlet{

	/**
	 *
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			String currentUrl = req.getParameter("currentUrl");
			
			MemberVo vo = new MemberVo();
			
			vo.setMemberId(memberId);
			vo.setMemberPwd(memberPwd);
			
			MemberService ms = new MemberService();
			MemberVo loginMember = ms.login(vo);
			
			ExpertVo loginExpert = null;
			if(loginMember != null) {
				loginExpert = ms.searchExpertInfo(loginMember);
			}
			
			if(loginMember != null) {
				req.getSession().setAttribute("loginMember", loginMember);
				req.getSession().setAttribute("loginExpert", loginExpert);
				resp.sendRedirect(currentUrl);
				
			}else {
				return;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("로그인 중 에러 발생");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "로그인 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
			
			
		}
		
		
		
		
	}
	
	
}
