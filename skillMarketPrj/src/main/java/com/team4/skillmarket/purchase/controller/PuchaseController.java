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

import com.google.gson.*;

@WebServlet("/purchase")
public class PuchaseController extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			Gson gson = new Gson();
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			String no = req.getParameter("no");
			
			//서비스
			PurchaseService purchaseService = new PurchaseService();
			CashService cashService = new CashService();
			
			// 견적서 정보
			InfoVo infoVo = purchaseService.getInfo(no);
			// 옵션 정보
			List<OptionVo> optionList = purchaseService.getOptionList(no);
			// 유저 캐시 정보
			UserCashVo userCash = cashService.getMemberCash(loginMember.getMemberNo());
			
			if(infoVo == null || userCash == null) {
				throw new IllegalStateException("구매페이지 조회 실패");
			}
			
			req.setAttribute("infoVo", infoVo);
			req.setAttribute("optionList", gson.toJson(optionList));
			req.setAttribute("userCash", gson.toJson(userCash));
			req.getRequestDispatcher("/WEB-INF/views/purchase/purchase.jsp").forward(req, resp);
			
		}

		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			String estimateNo = req.getParameter("estimateNo");
			String[] estimateOptions = req.getParameterValues("estimateOption");
			String[] quantitys = req.getParameterValues("quantity");
			String purchaseMethod = req.getParameter("purchaseMethod");
			
			System.out.println(estimateNo);
			for(String option : estimateOptions) {
				System.out.println(option);
			}
			for(String quantity : quantitys) {
				System.out.println(quantity);
			}
			System.out.println(purchaseMethod);
			
			// 주문서 추가
			// 주문서 옵션 추가 ( 있다면 )
			// Sales에 구매자 수수료 3% 추가
		
		}
		
		
}
