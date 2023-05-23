package com.team4.skillmarket.csc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.csc.service.CSCService;

@WebServlet("/notice")
public class NoticeController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			CSCService cs = new CSCService();
			List<noticeListVo> noticeList = cs.getNotice();
			
			req.setAttribute("noticeList", noticeList);
			req.getRequestDispatcher("/WEB-INF/views/csc/notice.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("공지사항 조회 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "공지사항 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);		
			
		}
		
	}
	
} // class
