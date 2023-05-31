package com.team4.skillmarket.expert.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.order.vo.QuotationSerachVo;
import com.team4.skillmarket.order.vo.QuotationStatusVo;
import com.team4.skillmarket.order.vo.QuotationViewVo;

@WebServlet("/expert/sale-list")
public class ExpertSaleListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			ExpertVo loginExpert = (ExpertVo) req.getSession().getAttribute("loginExpert");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/home");
				
				return;
			}
			
			if(loginExpert == null) {
				req.getSession().setAttribute("alertMsg", "전문가 등록을 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/expert/register");
				
				return;
			}
			
			MemberService ms = new MemberService();
			List<QuotationViewVo> saleList = ms.getSaleList(loginExpert);
			
			int progress = 0;
			int complete = 0;
			int cancel = 0;
			
			for(QuotationViewVo sale : saleList) {
				if("1".equals(sale.getQuotationStatusNo())) {
					progress += 1;
				}else if("2".equals(sale.getQuotationStatusNo())) {
					complete += 1;
				}else if("4".equals(sale.getQuotationStatusNo())) {
					cancel += 1;
				}
			}

			QuotationStatusVo vo = new QuotationStatusVo();
			vo.setProgress(Integer.toString(progress));
			vo.setComplete(Integer.toString(complete));
			vo.setCancel(Integer.toString(cancel));
			
			req.setAttribute("vo", vo);
			req.setAttribute("saleList", saleList);
			req.getRequestDispatcher("/WEB-INF/views/expert/salelist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			ExpertVo loginExpert = (ExpertVo) req.getSession().getAttribute("loginExpert");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/home");
				
				return;
			}
			
			if(loginExpert == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/expert/register");
				
				return;
			}
			
			String saleStatus = req.getParameter("saleStatus");
			String saleDate1 = req.getParameter("saleDate1");
			String saleDate2 = req.getParameter("saleDate2");
			String saleSearch = req.getParameter("saleSearch");
			
			QuotationSerachVo qvo = new QuotationSerachVo();
			
			qvo.setMemberNo(loginExpert.getFreelancerNo());
			qvo.setOrderSearch(saleSearch);
			qvo.setOrderDate1(saleDate1);
			qvo.setOrderDate2(saleDate2);
			qvo.setOrderStatus(saleStatus);
			
			
			MemberService ms = new MemberService();
			List<QuotationViewVo> saleList = ms.getSaleList(loginExpert);
			List<QuotationViewVo> searchList = ms.getSearchSaleList(qvo);
			
			int progress = 0;
			int complete = 0;
			int cancel = 0;
			
			for(QuotationViewVo sale : saleList) {
				if("1".equals(sale.getQuotationStatusNo())) {
					progress += 1;
				}else if("2".equals(sale.getQuotationStatusNo())) {
					complete += 1;
				}else if("4".equals(sale.getQuotationStatusNo())) {
					cancel += 1;
				}
			}

			QuotationStatusVo vo = new QuotationStatusVo();
			vo.setProgress(Integer.toString(progress));
			vo.setComplete(Integer.toString(complete));
			vo.setCancel(Integer.toString(cancel));
			
			req.setAttribute("vo", vo);
			req.setAttribute("saleList", searchList);
			req.getRequestDispatcher("/WEB-INF/views/expert/salelist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
