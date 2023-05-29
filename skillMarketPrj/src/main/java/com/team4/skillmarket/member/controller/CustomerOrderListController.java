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
import com.team4.skillmarket.order.vo.QuotationSerachVo;
import com.team4.skillmarket.order.vo.QuotationStatusVo;
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
			
			int progress = 0;
			int complete = 0;
			int cancel = 0;
			
			for(QuotationViewVo order : orderList) {
				if("1".equals(order.getQuotationStatusNo())) {
					progress += 1;
				}else if("2".equals(order.getQuotationStatusNo())) {
					complete += 1;
				}else if("4".equals(order.getQuotationStatusNo())) {
					cancel += 1;
				}
			}

			QuotationStatusVo vo = new QuotationStatusVo();
			vo.setProgress(Integer.toString(progress));
			vo.setComplete(Integer.toString(complete));
			vo.setCancel(Integer.toString(cancel));
			
			req.setAttribute("vo", vo);
			req.setAttribute("orderList", orderList);
			req.getRequestDispatcher("/WEB-INF/views/member/orderlist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/home");
				
				return;
			}
			
			String orderStatus = req.getParameter("orderStatus");
			String orderDate1 = req.getParameter("orderDate1");
			String orderDate2 = req.getParameter("orderDate2");
			String orderSearch = req.getParameter("orderSearch");
			
			QuotationSerachVo qvo = new QuotationSerachVo();
			
			qvo.setMemberNo(loginMember.getMemberNo());
			qvo.setOrderSearch(orderSearch);
			qvo.setOrderDate1(orderDate1);
			qvo.setOrderDate2(orderDate2);
			qvo.setOrderStatus(orderStatus);
			
			System.out.println(qvo);
			
			MemberService ms = new MemberService();
			List<QuotationViewVo> orderList = ms.getOrderList(loginMember);
			List<QuotationViewVo> searchList = ms.getSearchOrderList(qvo);
			
			int progress = 0;
			int complete = 0;
			int cancel = 0;
			
			for(QuotationViewVo order : orderList) {
				if("1".equals(order.getQuotationStatusNo())) {
					progress += 1;
				}else if("2".equals(order.getQuotationStatusNo())) {
					complete += 1;
				}else if("4".equals(order.getQuotationStatusNo())) {
					cancel += 1;
				}
			}

			QuotationStatusVo vo = new QuotationStatusVo();
			vo.setProgress(Integer.toString(progress));
			vo.setComplete(Integer.toString(complete));
			vo.setCancel(Integer.toString(cancel));
			
			req.setAttribute("vo", vo);
			req.setAttribute("orderList", searchList);
			req.getRequestDispatcher("/WEB-INF/views/member/orderlist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
