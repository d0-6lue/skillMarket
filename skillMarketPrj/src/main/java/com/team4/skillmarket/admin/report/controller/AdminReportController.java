package com.team4.skillmarket.admin.report.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.admin.report.service.roportListService;
import com.team4.skillmarket.admin.report.vo.AdminReportStatusVo;
import com.team4.skillmarket.admin.report.vo.reportListVo;

@WebServlet("/admin/report")
public class AdminReportController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		try {
			
			roportListService rs = new roportListService();
			List<reportListVo> reprotArrList = rs.selectReportList();
			
			//상태 체크
			int statusYCnt = 0;
			int statusNCnt = 0;
			for(reportListVo e: reprotArrList) {
				if (e.getRptStatus().equals("Y")) {
					statusYCnt ++;
					e.setRptStatus("✔️");
				}else {
					statusNCnt ++;
					e.setRptStatus("❌");
				}
			}
			
			AdminReportStatusVo msVo = new AdminReportStatusVo();
			msVo.setStatusYCnt(statusYCnt);
			msVo.setStatusNCnt(statusNCnt);
			
			req.setAttribute("msVo", msVo);
			req.setAttribute("reprotArrList", reprotArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/report.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}