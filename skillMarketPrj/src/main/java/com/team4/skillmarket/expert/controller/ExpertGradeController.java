package com.team4.skillmarket.expert.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.expert.service.ExpertService;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/expert/grade")
public class ExpertGradeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			ExpertVo loginExpert = (ExpertVo) req.getSession().getAttribute("loginExpert");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/home");
				
				return;
			}
			
			if(loginExpert == null) {
				req.getSession().setAttribute("alertMsg", "전문가 등록을 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/expert/register");
				
				return;
			}
			
			
			
			ExpertService es = new ExpertService();
			ExpertVo evo = es.getGrade(loginExpert);
			String salesRate = es.getCompleteCount(loginExpert);
			String salesMoney = es.getCompletePrice(loginExpert);
			if(salesMoney == null) {
				salesMoney = "0";
			}
			
			if(evo == null) {
				new IllegalStateException();
			}
			
			req.setAttribute("salesRate", salesRate);
			req.setAttribute("salesMoney", salesMoney);
			req.setAttribute("evo", evo);
			req.getRequestDispatcher("/WEB-INF/views/expert/grade.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "스킬등급 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
}
