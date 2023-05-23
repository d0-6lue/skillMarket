package com.team4.skillmarket.admin.home.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.home.service.HomeService;
import com.team4.skillmarket.admin.home.vo.HomeVo;
import com.team4.skillmarket.admin.home.vo.MonthStatsVo;

@WebServlet("/admin/home")
public class AdminHomeController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		try {
			
			HomeService hs = new  HomeService();
			HomeVo homeVo =  hs.getListByHome();
			List<MonthStatsVo> monthStatsList = hs.getMonthlySalesAndSignupStats();
			
			
			
			if (homeVo == null) {
				throw new IllegalStateException("홈화면의 모든 정보가 null 확인 필요");
			}
			if (monthStatsList == null) {
				throw new IllegalStateException("홈화면의 통계 리스트가 null 확인 필요");
			}
			
			req.setAttribute("monthStatsList", monthStatsList);
			req.setAttribute("homeVo", homeVo);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/home.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
