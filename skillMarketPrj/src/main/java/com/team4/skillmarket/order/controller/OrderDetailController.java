package com.team4.skillmarket.order.controller;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.order.service.OrderService;
import com.team4.skillmarket.order.vo.QuotationVo;

@WebServlet("/order/detail")
public class OrderDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 주문서 번호 (?no=)
		String quatationNo = req.getParameter("no");
		
		
		// 주문서 번호(quatationNo) -> 주문서 정보(quatationVo), 견적서 정보(estimateVo)
		QuotationVo quatationVo = new QuotationVo();
		EstimateVo estimateVo= new EstimateVo();
		
		Map<String, Object> voMap = new HashMap<>();
		
		
		// Service
		OrderService orderService = new OrderService();
		voMap = orderService.getDetail(quatationNo);
		
		
		

		req.getRequestDispatcher("/WEB-INF/views/order/orderDetail.jsp").forward(req, resp);
	
	}
	
}
