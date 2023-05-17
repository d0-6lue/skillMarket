package com.team4.skillmarket.expert.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.expert.vo.ExpertVo;

@WebServlet("/expert-info")
public class ExpertInfoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ExpertVo evo = (ExpertVo) req.getSession().getAttribute("loginExpert");
		
		if(evo == null) {
			req.getSession().setAttribute("alertMsg", "전문가 등록을 먼저 해주세요.");
			resp.sendRedirect(req.getContextPath() + "/expert/register");
			
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/expert/expertinfo.jsp").forward(req, resp);
		
	}
	
}
