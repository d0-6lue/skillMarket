package com.team4.skillmarket.csc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.inquiry.vo.InquiryVo;
import com.team4.skillmarket.csc.service.CSCService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/inquiry-detail")
public class InquiryDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요");
				resp.sendRedirect(req.getContextPath() + "/csc");
				
				return;
			}
			
			String no = req.getParameter("no");
			
			CSCService cs = new CSCService();
			String comment = cs.checkInquiryComment(no);
			InquiryVo ivo = cs.getInquiryByNo(no,loginMember,comment);
			
			req.setAttribute("ivo", ivo);
			req.getRequestDispatcher("/WEB-INF/views/csc/inquirydetail.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "문의 상세내용 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
		
	}
	
	
}
