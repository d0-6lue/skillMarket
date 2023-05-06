package com.team4.skillmarket.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/community/post/write")
public class CommunityWriteController extends HttpServlet{
	
	//커뮤니티 글쓰기 화면 조회하기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//대충 로그인 검증 로직하기 ~
		
		req.getRequestDispatcher("/WEB-INF/views/community/post/write.jsp").forward(req, resp);
	}
	
	//커뮤니티에 글쓰기 ~
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}

}
