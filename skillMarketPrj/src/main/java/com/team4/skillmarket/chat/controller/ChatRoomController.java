package com.team4.skillmarket.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.chat.service.ChatService;
import com.team4.skillmarket.chat.vo.ChatRoomSideInfoVo;

@WebServlet("/chat/room")
public class ChatRoomController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String quotationNo = req.getParameter("no");
			
			
			ChatRoomSideInfoVo sideInfo = new ChatRoomSideInfoVo();
			
			ChatService chatService = new ChatService();
			
			sideInfo = chatService.getSideInfo(quotationNo);
			
			if(sideInfo != null) {
				
				req.setAttribute("sideInfo", sideInfo);
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
