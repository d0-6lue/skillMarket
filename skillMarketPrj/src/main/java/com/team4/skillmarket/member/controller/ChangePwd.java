package com.team4.skillmarket.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/my-info/change-pwd")
public class ChangePwd extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVo mvo = (MemberVo) req.getSession().getAttribute("loginMember");
		
		if(mvo != null) {
			req.getRequestDispatcher("/WEB-INF/views/member/changepwd.jsp").forward(req, resp);
			
		}else {
			req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요.");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo mvo = (MemberVo) req.getSession().getAttribute("loginMember");
			String changePwd = req.getParameter("changePwd");
			
			MemberVo vo = new MemberVo();
			vo.setMemberNo(mvo.getMemberNo());
			vo.setMemberPwd(changePwd);
			
			MemberService ms = new MemberService();
			int result = ms.changePwd(vo);
			
			if(result == 1) {
				req.getSession().invalidate();
				req.getSession().setAttribute("alertMsg", "비밀번호 변경 완료!");
				resp.sendRedirect(req.getContextPath() + "/home");
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("비밀번호 변경 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "비밀번호 변경 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}

}
