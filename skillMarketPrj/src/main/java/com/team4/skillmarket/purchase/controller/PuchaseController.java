package com.team4.skillmarket.purchase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.cash.service.CashService;
import com.team4.skillmarket.cash.vo.UserCashVo;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.purchase.service.PurchaseService;
import com.team4.skillmarket.purchase.vo.InfoVo;
import com.team4.skillmarket.purchase.vo.OptionVo;

@WebServlet("/purchase")
public class PuchaseController extends HttpServlet {

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			String no = req.getParameter("no");
			
			PurchaseService purchaseService = new PurchaseService();
			InfoVo infoVo = purchaseService.getInfo(no);
			List<OptionVo> optionList = purchaseService.getOptionList(no);
			CashService cashService = new CashService();
			UserCashVo userCash = cashService.getMemberCash(no);
			
			// 견적서 정보
			// 옵션 정보
			// 유저 캐시 정보
			req.getRequestDispatcher("/WEB-INF/views/purchase/purchase.jsp").forward(req, resp);
			
		}
	
}
