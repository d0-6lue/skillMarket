package com.team4.skillmarket.admin.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.inquiry.service.inquiryListService;
import com.team4.skillmarket.admin.inquiry.vo.InquiryVo;

@WebServlet("/admin/inquiry/answer")
public class AdminInquiryAnswerController extends HttpServlet{
	
	private final inquiryListService is = new inquiryListService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String no = req.getParameter("no");
			String answer = req.getParameter("answer");
			
			InquiryVo vo = new InquiryVo();
			vo.setQnaNo(no);
			vo.setQuestionAnswerContent(answer);;
			
			int result =  is.answerInquiry(vo);
			
			if (result < 1) {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("error");
			}
			
			resp.setCharacterEncoding("UTF-8");
	        resp.getWriter().write("ok");
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
			
		}
		
		
		
	}
	
}
