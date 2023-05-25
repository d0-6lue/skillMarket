package com.team4.skillmarket.expert.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.expert.service.ExpertService;
import com.team4.skillmarket.expert.vo.ExpertVo;

@WebServlet("/expert/grade")
public class ExpertGradeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			ExpertVo loginExpert = (ExpertVo) req.getSession().getAttribute("loginExpert");
			
			ExpertService es = new ExpertService();
			ExpertVo evo = es.getGrade(loginExpert);
			
			if(evo == null) {
				new IllegalStateException();
			}
			
			req.setAttribute("evo", evo);
			req.getRequestDispatcher("/WEB-INF/views/expert/grade.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "스킬등급 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
}
