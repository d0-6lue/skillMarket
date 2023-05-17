package com.team4.skillmarket.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/join/normal")
public class MemberJoinNormalController extends HttpServlet{

	// 회원가입 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberService ms = new MemberService();
			List<EstimateCategoryVo> categoryList = ms.searchCategoryJoin();
			
			if(categoryList != null) {
				
				req.setAttribute("categoryList", categoryList);
				req.getRequestDispatcher("/WEB-INF/views/member/joinNormal.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
	// 회원가입
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memberName = req.getParameter("memberName");
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			String memberNick = req.getParameter("memberNick");
			String memberEmail = req.getParameter("memberEmail");
			String memberAddress = req.getParameter("memberAddress") + "," + req.getParameter("memberAddress2");
			
			String memberPhone = req.getParameter("memberPhone");
			String memberBank = req.getParameter("memberBank");
			String memberAccount = req.getParameter("memberAccount");
			String memberInterst = req.getParameter("memberFavorite");
			
			MemberVo vo = new MemberVo();
			vo.setMemberName(memberName);
			vo.setMemberId(memberId);
			vo.setMemberPwd(memberPwd);
			vo.setMemberNick(memberNick);
			vo.setMemberEmail(memberEmail);
			vo.setMemberAddress(memberAddress);
			vo.setMemberPhone(memberPhone);
			vo.setMemberBank(memberBank);
			vo.setMemberAccount(memberAccount);
			vo.setMemberAddress(memberAddress);
			vo.setMemberInterst(memberInterst);
			
			// 서비스
			MemberService ms = new MemberService();
			int result = ms.join(vo);
			
			if(result == 1) {
				req.getSession().setAttribute("loginMember", vo);
				String path = req.getContextPath();
				resp.sendRedirect(path + "/home");
			}else {
				throw new Exception();
			}
			
			// 화면
		} catch (Exception e) {
			System.out.println("회원가입 중 에러 발생..");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "회원가입 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/view/common/error.jsp").forward(req, resp);
		}
		
		
		
	}
	
}
