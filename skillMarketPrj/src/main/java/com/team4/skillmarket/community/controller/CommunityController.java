package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.team4.skillmarket.community.vo.FreeBoardVo;

@WebServlet("/community/*")
public class CommunityController extends HttpServlet {
    
    //커뮤니티 조회하기 ~
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	request.getRequestDispatcher("/WEB-INF/views/community/board.jsp").forward(request, response);
    }
    
    //커뮤니티 게시글 작성
    
   
    	
}



