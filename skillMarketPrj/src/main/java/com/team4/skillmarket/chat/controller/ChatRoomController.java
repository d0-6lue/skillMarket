package com.team4.skillmarket.chat.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.chat.service.ChatService;
import com.team4.skillmarket.chat.vo.*;

@WebServlet("/chat/room")
public class ChatRoomController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String quotationNo = req.getParameter("no");
			
			ChatService chatService = new ChatService();
			
			ChatRoomSideInfoVo sideInfo = new ChatRoomSideInfoVo();
			sideInfo = chatService.getSideInfo(quotationNo);
			
			List<RequestCategoryVo> requestCatVoList = new ArrayList<>();
			requestCatVoList = chatService.getRequestCat();
			
			
			if(sideInfo != null) {
				
				req.setAttribute("sideInfo", sideInfo);
				req.setAttribute("categoryList", requestCatVoList);
				req.getRequestDispatcher("/WEB-INF/views/chat/chatRoom.jsp").forward(req, resp);
				
			}
			else {
				throw new Exception();
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "오류");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
	
	}
	
}
