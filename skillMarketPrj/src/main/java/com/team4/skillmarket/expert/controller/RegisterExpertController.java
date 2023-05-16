package com.team4.skillmarket.expert.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.service.ExpertService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/expert/register")
public class RegisterExpertController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			ExpertService es = new ExpertService();
			List<EstimateCategoryVo> categoryList = es.getSelectCategory();
			
			if(loginMember != null) {
				req.setAttribute("categoryList", categoryList);
				req.getRequestDispatcher("/WEB-INF/views/expert/register.jsp").forward(req, resp);
			}else {
				req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요.");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", e);
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	}

}
