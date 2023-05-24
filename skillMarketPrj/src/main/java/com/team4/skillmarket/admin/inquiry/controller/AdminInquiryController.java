package com.team4.skillmarket.admin.inquiry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.inquiry.service.inquiryListService;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.admin.member.vo.memberListVo;


@WebServlet("/admin/inquiry")
public class AdminInquiryController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			inquiryListService is = new inquiryListService();
			List<inquiryListVo> inquiryArrList = is.selectInquiryList();
			
			//상태 체크
			int statusYCnt = 0;
			int statusNCnt = 0;
			for(inquiryListVo e: inquiryArrList) {
				if (e.getQnaStatus().equals("Y")) {
					statusYCnt ++;
					e.setQnaStatus("✔️");
				}else {
					statusNCnt ++;
					e.setQnaStatus("❌");
				}
			}
			
			
			req.setAttribute("statusYCnt", statusYCnt);
			req.setAttribute("statusNCnt", statusNCnt);
			req.setAttribute("inquiryArrList", inquiryArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/inquiry.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}