package com.team4.skillmarket.expert.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.estimate.vo.EstimateViewVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/expert/QNA-mgmt")
public class ExpertQNAMgmtController extends HttpServlet{

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
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/expert/register");
				
				return;
			}
			
			MemberService ms = new MemberService();
			List<EstimateViewVo> estimateList = ms.getEstimateList(loginExpert);
//			ms.get
			
//			req.setAttribute("estimateList", estimateList);
			req.getRequestDispatcher("/WEB-INF/views/expert/qnamgmt.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "견적서 조회 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
		
	}
	
}
