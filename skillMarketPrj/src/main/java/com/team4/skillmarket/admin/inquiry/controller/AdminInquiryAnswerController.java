package com.team4.skillmarket.admin.inquiry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team4.skillmarket.admin.inquiry.service.inquiryListService;
import com.team4.skillmarket.admin.inquiry.vo.InquiryVo;
import com.team4.skillmarket.admin.inquiry.vo.StatusCountVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;

@WebServlet("/admin/inquiry/answer")
public class AdminInquiryAnswerController extends HttpServlet{
	
	private final inquiryListService is = new inquiryListService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String no = req.getParameter("no");
			String answer = req.getParameter("answer");
			String empty = req.getParameter("empty");
			
			
			InquiryVo vo = new InquiryVo();
			vo.setQnaNo(no);
			vo.setQuestionAnswerContent(answer);
			
			int result = 0;
			if (empty.equals("empty")) {
				result =  is.answerInquiry(vo);
			}
			else {
				result =  is.updateAnswerInquiry(vo);
			}
			
			List<inquiryListVo> inquiryArrList = is.selectInquiryList();
			List<inquiryListVo> inquiryCatList = is.selectInquiryCatList();
			
			
			//상태 체크
			int statusYCnt = 0;
			int statusNCnt = 0;
			for(inquiryListVo e: inquiryArrList) {
				if (e.getQnaAnswerStatus().equals("N")) {
					statusNCnt ++;
				}else {
					statusYCnt ++;
				}
			}
			
			
			if (result < 1) {
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write("error");
			}
			
			StatusCountVo statusCount = new StatusCountVo();
			statusCount.setStatusYCnt(statusYCnt);
			statusCount.setStatusNCnt(statusNCnt);;
			
			Gson gson = new Gson();
			String count = gson.toJson(statusCount);

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(count);
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
			
		}
		
		
		
	}
	
}
