package com.team4.skillmarket.admin.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.CacheHttpServlet;
import com.team4.skillmarket.admin.notice.service.noticeListService;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;

@WebServlet("/admin/notice/edit")
public class AdminNoticeEditController extends CacheHttpServlet{
	
	private final noticeListService ns = new noticeListService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String no = req.getParameter("no");
			String title = req.getParameter("title");
			String select = req.getParameter("select");
			String content = req.getParameter("content");
			
			noticeListVo vo = new noticeListVo();
			vo.setNotiNo(no);
			vo.setNotiTitle(title);
			vo.setNotiCatNo(select);
			vo.setNotiContent(content);
			
			int editNotice =  ns.editNotice(vo);
			
			Gson gson = new Gson();
			
			List<noticeListVo> noticeSelectList = ns.noticeSelectList();
			
			if (editNotice != 1 || noticeSelectList == null) {
				
				resp.setCharacterEncoding("UTF-8"); 
		        PrintWriter writer = resp.getWriter();
		        writer.println("업데이트 실패");
		        throw new IllegalStateException("업데이트 실패");
			}
			
			String update = gson.toJson(noticeSelectList);
	        resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8"); 
	        PrintWriter writer = resp.getWriter();
	        writer.println(update);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
		
		
	}
	
}	
