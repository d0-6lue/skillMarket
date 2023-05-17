package com.team4.skillmarket.admin.notice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit.Parser;

import com.google.gson.Gson;
import com.team4.skillmarket.admin.notice.service.noticeListService;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.admin.notice.service.noticeListService;
import com.team4.skillmarket.admin.notice.vo.AdminNoticeDto;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;


@WebServlet("/admin/notice")
public class AdminNoticeController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		try {
					
			noticeListService ns = new noticeListService();
			List<noticeListVo> noticeArrList = ns.selectNoticeList();
			
			
			req.setAttribute("noticeArrList", noticeArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/notice.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		
		
			
			
		// 요청의 body 값을 읽기 위한 BufferedReader	
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		StringBuilder sb = new StringBuilder();	
		String line;
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		
		// body 값을 문자열로 가져옴
        String reqBody = sb.toString();
        
        // JSON 문자열을 자바 객체로 변환 (Gson 사용)
        Gson gson = new Gson();
        AdminNoticeDto dto = gson.fromJson(reqBody, AdminNoticeDto.class);
        
        
        
        // 자바 객체의 값들을 사용
        String title = dto.getTitle();
        String content = dto.getContent();
        String category = dto.getCategory();
        String[] images = dto.getImages();
        
        
        
     

 		System.out.println(content);
        
        // 데이터 처리
        
        
        // 응답 설정
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        // 응답 데이터 전송
        PrintWriter writer = resp.getWriter();
        writer.write("{\"result\": \"success\"}");
        writer.close();
        
        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

