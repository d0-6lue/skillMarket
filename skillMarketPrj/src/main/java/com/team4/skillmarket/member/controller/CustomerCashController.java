package com.team4.skillmarket.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.cash.vo.CashVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/customer/cash")
public class CustomerCashController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			MemberService ms = new MemberService();
			List<CashVo> cashList = ms.getCash(loginMember);
			
			int total = 0;
			int bonus = 0;
			int real = 0;
			for(CashVo cash : cashList) {
				real += Integer.parseInt(cash.getCashMoney());
				bonus += Integer.parseInt(cash.getCashPoint());
			}
			total = bonus + real;
			
			CashVo cvo = new CashVo();
			cvo.setCashMoney(Integer.toString(real));
			cvo.setCashPoint(Integer.toString(bonus));
			cvo.setCashTotal(Integer.toString(total));
			
			req.setAttribute("cashVo", cvo);
			req.getRequestDispatcher("/WEB-INF/views/member/cash.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("캐시 조회 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "캐시 조회 중 에러 발생..");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
	}
	
}
