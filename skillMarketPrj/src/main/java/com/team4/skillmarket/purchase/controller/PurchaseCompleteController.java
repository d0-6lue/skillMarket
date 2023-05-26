package com.team4.skillmarket.purchase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.order.vo.QuotationVo;
import com.team4.skillmarket.purchase.service.PurchaseService;
import com.team4.skillmarket.purchase.vo.QuotationOptionVo;

@WebServlet("/purchase/completed")
public class PurchaseCompleteController extends HttpServlet {

	private final PurchaseService purchaseSerivce = new PurchaseService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String quotationNo = req.getParameter("no");
			
			QuotationVo quotaionVo = purchaseSerivce.getQuotationInfo(quotationNo);
			List<QuotationOptionVo> quotationOptionList = purchaseSerivce.getQuotationOptionList(quotationNo);
			
			if(quotaionVo == null || quotationOptionList.isEmpty()) {
				throw new Exception();
			}
			
			req.setAttribute("quotationVo", quotaionVo);
			req.setAttribute("quotationOptionList", quotationOptionList);
			req.getRequestDispatcher("/WEB-INF/views/purchase/purchaseCompleted.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
