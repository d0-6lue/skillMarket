package com.team4.skillmarket.csc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.notice.vo.NoticeListVo;
import com.team4.skillmarket.csc.service.CSCService;

@WebServlet("/csc")
public class CSCController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CSCService cs = new CSCService();
		List<NoticeListVo> vo = cs.getNotice();
		
		req.getRequestDispatcher("/WEB-INF/views/csc/csc.jsp").forward(req, resp);
		
	}
	
}
