package com.team4.skillmarket.csc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.csc.service.CSCService;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String no = req.getParameter("no");
			
			CSCService cs = new CSCService();
			
			int result = cs.addHit(no);
			if(result != 1) {
				throw new Exception("조회수 에러");
			}
			
			noticeListVo noticeVo = cs.getNoticeByNo(no);
			
			req.setAttribute("noticeVo", noticeVo);
			req.getRequestDispatcher("/WEB-INF/views/csc/noticedetail.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "공지사항 상세 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
}
