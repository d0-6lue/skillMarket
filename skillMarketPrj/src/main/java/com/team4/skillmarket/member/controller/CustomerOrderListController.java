package com.team4.skillmarket.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.order.vo.QuotationViewVo;

@WebServlet("/customer/order-list")
public class CustomerOrderListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/home");
				
				return;
			}
			
			MemberService ms = new MemberService();
			List<QuotationViewVo> orderList = ms.getOrderList(loginMember);

			
			req.setAttribute("orderList", orderList);
			req.getRequestDispatcher("/WEB-INF/views/member/orderlist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	}
	
}
