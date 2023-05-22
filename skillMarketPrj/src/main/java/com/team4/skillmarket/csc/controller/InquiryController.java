package com.team4.skillmarket.csc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.inquiry.dao.inquiryListDao;
import com.team4.skillmarket.admin.inquiry.vo.InquiryCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.csc.service.CSCService;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/inquiry")
public class InquiryController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인 먼저 해주세요.");
				resp.sendRedirect(req.getContextPath() + "/csc");
				return ;
			}
			
			CSCService cs = new CSCService();
			List<InquiryCategoryVo> inquriyCategoryList = cs.getInquiryCategory();
			
			if(inquriyCategoryList.size() > 0) {
				req.setAttribute("inquriyCategoryList", inquriyCategoryList);
				req.getRequestDispatcher("/WEB-INF/views/csc/inquiry.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("카테고리 조회 에러....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "카테고리 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			String title = req.getParameter("inquiryTitle");
			String content = req.getParameter("inquiryContent");
			String category = req.getParameter("inuqiryCategroy");
			
			inquiryListVo ivo = new inquiryListVo();
			ivo.setMemberNo(loginMember.getMemberNo());
			ivo.setQnaTitle(title);
			ivo.setQnaContent(content);
			ivo.setQnaCatNo(category);
			
			CSCService cs = new CSCService();
			int result = cs.inquiry(ivo);
			
			if(result == 1) {
				req.getSession().setAttribute("alertMsg", "문의사항이 등록됐습니다.");
				resp.sendRedirect(req.getContextPath() + "/csc");
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("문의사항 등록 중 에러 발생");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "문의사항 등록 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
} // class
