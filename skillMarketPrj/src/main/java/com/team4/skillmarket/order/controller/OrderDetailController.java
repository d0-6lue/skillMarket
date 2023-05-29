package com.team4.skillmarket.order.controller;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.order.service.OrderService;
import com.team4.skillmarket.order.vo.QuotationOptionVo;
import com.team4.skillmarket.order.vo.QuotationVo;
import com.team4.skillmarket.chat.vo.ChatVo;

@WebServlet("/order/detail")
public class OrderDetailController extends HttpServlet {

	private final OrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 하셔야 합니다.");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			
			String memberNo = loginMember.getMemberNo();
			// 주문서 번호 (?no=)
			String quotationNo = req.getParameter("no");
			
			
			// 주문서 번호(quatationNo) -> 주문서 정보(quatationVo), 견적서 정보(estimateVo)
			QuotationVo quotationVo = new QuotationVo();
			List<QuotationOptionVo> optionVoList = new ArrayList<>();
			
			Map<String, Object> voMap = new HashMap<>();
			
			// Service
			voMap = orderService.getOrderDetailbyNo(quotationNo);
			
			quotationVo = (QuotationVo) voMap.get("quotation");
			optionVoList = (List<QuotationOptionVo>) voMap.get("optionList");
			
			String lastChatContent = orderService.getLastChat(quotationNo, memberNo);
			
			if(quotationVo != null) {
				
				req.setAttribute("quotationVo", quotationVo);
				if(!optionVoList.isEmpty()) {
					req.setAttribute("optionVoList", optionVoList);
				}
				req.setAttribute("lastChatContent", lastChatContent);
				
				req.getRequestDispatcher("/WEB-INF/views/order/orderDetail.jsp").forward(req, resp);
				
			}
			else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "errorMsg");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}

		
	
	}
	
}
