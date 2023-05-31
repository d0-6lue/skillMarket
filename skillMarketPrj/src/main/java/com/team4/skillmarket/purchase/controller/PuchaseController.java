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
import com.team4.skillmarket.purchase.vo.WriteQuotationVo;
import com.google.gson.*;

@WebServlet("/purchase")
public class PuchaseController extends HttpServlet {
	
		private final PurchaseService perchaseService = new PurchaseService();

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			Gson gson = new Gson();
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 하셔야 합니다.");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			
			String no = req.getParameter("no");
			String seller = req.getParameter("seller");
			//서비스
			CashService cashService = new CashService();
			
			// 견적서 정보
			InfoVo infoVo = perchaseService.getInfo(no, seller);
			// 옵션 정보
			List<OptionVo> optionList = perchaseService.getOptionList(no);
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
			
			try {
				
				// 데이터
				HttpSession session = req.getSession();
				MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
				
				String memberNo = loginMember.getMemberNo();
				String estimateNo = req.getParameter("estimateNo");
				String totalPeriod = req.getParameter("totalPeriod");
				String totalPrice = req.getParameter("totalPrice");
				
				String[] estimateOptions = req.getParameterValues("estimateOption");
				String[] quantitys = req.getParameterValues("quantity");
				
				String purchaseMethod = req.getParameter("purchaseMethod");
				
				WriteQuotationVo writeQuotationVo = new WriteQuotationVo();
				
				writeQuotationVo.setMemberNo(memberNo);
				writeQuotationVo.setEstimateNo(estimateNo);
				writeQuotationVo.setTotalPeriod(totalPeriod);
				
				writeQuotationVo.setTotalPrice(totalPrice);
				writeQuotationVo.setEstimateOptions(estimateOptions);
				writeQuotationVo.setQuantitys(quantitys);
				
				writeQuotationVo.setPurchaseMethod(purchaseMethod);
				
				// 서비스
				// 주문서 추가
				// 주문서 옵션 추가 ( 있다면 )
				// 구매자 캐시 감소
				// Sales에 구매자 수수료 3% 추가
				String quotationNo = perchaseService.writeQuotation(writeQuotationVo);
				
				if(quotationNo == null) {
					throw new IllegalStateException();
				}
				
				// 결과 처리
				resp.sendRedirect(req.getContextPath() + "/purchase/completed?no=" + quotationNo);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				
			}
		}
}
