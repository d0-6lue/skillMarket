package com.team4.skillmarket.cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.cash.service.CashService;
import com.team4.skillmarket.cash.vo.CashChargeVo;
import com.team4.skillmarket.cash.vo.UserCashVo;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/cash/charge")
public class ChargeCashContorller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberVo loginMember= (MemberVo) session.getAttribute("loginMember");
		
		if(loginMember == null) {
			req.getSession().setAttribute("alertMsg", "로그인 하셔야 합니다.");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		CashService cashSerivce = new CashService();
		UserCashVo userCash = cashSerivce.getMemberCash(loginMember.getMemberNo());
		
		if(userCash == null) {
			throw new IllegalStateException();
		}
		
		req.setAttribute("userCash", userCash);
		req.getRequestDispatcher("/WEB-INF/views/cash/chargeCash.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			HttpSession session = req.getSession();
			MemberVo loginMember= (MemberVo) session.getAttribute("loginMember");
			
			String memberNo = loginMember.getMemberNo();
			String amount = req.getParameter("amount");
			String method = req.getParameter("purchase-method-radio");
			
			CashChargeVo chargeVo = new CashChargeVo();
			chargeVo.setMemberNo(memberNo);
			chargeVo.setPaymentMethodNo(method);
			chargeVo.setChargeAmount(amount.replaceAll("\\,", ""));
			
			CashService cashService = new CashService();
			int result = cashService.charge(chargeVo);
			
			if(result != 1) {
				throw new IllegalStateException();
			}
			
			resp.sendRedirect(req.getContextPath()+ "/customer/cash");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
