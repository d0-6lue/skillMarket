package com.team4.skillmarket.estimate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.estimate.vo.EstimateVo;

//견적서 등록된거 상세보기 페이지 ~
@WebServlet("/esti")
public class EstimateDetailController extends HttpServlet {
	
	private final EstimateService estimateService = new EstimateService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String estimateNo = req.getParameter("estimateNo");
			
			EstimateVo estimateVo = estimateService.getEstimateVoByNo(estimateNo);
			
			if(estimateVo == null) {
				throw new Exception("견적서 상세 조회 실패");
			}
			
			req.setAttribute("estimateVo", estimateVo);
			req.getRequestDispatcher("WEB-INF/views/estimate/detail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			req.setAttribute("errorMsg", "잘못된 접근입니다.");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}

}
