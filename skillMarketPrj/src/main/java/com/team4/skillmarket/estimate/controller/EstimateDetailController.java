package com.team4.skillmarket.estimate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//견적서 등록된거 상세보기 페이지 ~
@WebServlet("/esti")
public class EstimateDetailController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/estimate/detail.jsp").forward(req, resp);
	}

}
