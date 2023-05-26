package com.team4.skillmarket.admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.CacheHttpServlet;
import com.team4.skillmarket.admin.member.service.memberListService;
import com.team4.skillmarket.admin.member.vo.memberListVo;

@WebServlet("/admin/members/search")
public class AdminMemberSearchController extends CacheHttpServlet{
	
	private final memberListService ms = new memberListService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			List<memberListVo> memberArr = ms.searchMemberList(req.getParameter("userId"));
			
			if (memberArr == null) {
				resp.getWriter().write("일치하는 회원 없습니다 🤔");
				throw new IllegalStateException("일치하는 회원 없음");
			}
			
			Gson gson = new Gson();
			String memberArrList = gson.toJson(memberArr);
			
			req.setAttribute("memberArrList", memberArrList);
			resp.getWriter().write(memberArrList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
