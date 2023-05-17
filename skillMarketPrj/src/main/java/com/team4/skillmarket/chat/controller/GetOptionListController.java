package com.team4.skillmarket.chat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.chat.service.ChatService;
import com.team4.skillmarket.chat.vo.OptionVo;

import com.google.gson.*;

@WebServlet("/option/get")
public class GetOptionListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String value = req.getParameter("cat");
			String no = req.getParameter("no");
			
			ChatService chatService = new ChatService();
			
			List<OptionVo> optionList =  null;
			
			optionList = chatService.getOption(value, no);
			
			if( !(optionList.isEmpty()) ) {
				
				Gson gson = new Gson();
				
				String optionListGson = gson.toJson(optionList);
				
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter out = resp.getWriter();
				out.write(optionListGson);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
