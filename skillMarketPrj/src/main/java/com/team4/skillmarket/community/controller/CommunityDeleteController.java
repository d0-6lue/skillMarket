package com.team4.skillmarket.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.FreeBoardVo;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/community/post/delete")
public class CommunityDeleteController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	HttpSession session = req.getSession();
	MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
	
	if(loginMember == null) {
		throw new IllegalStateException("로그인하고오세요");
	}
	
	String no = req.getParameter("no");
	String writerNo = loginMember.getMemberNo();
	
	FreeBoardVo vo = new FreeBoardVo();
	vo.setBoardNo(no);
	vo.setMemberNo(writerNo);
	
	CommunityService cs = new CommunityService();
	int result = 0;
	try {
		result = cs.delte(vo);
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	
	if(result == 1) {
		resp.sendRedirect(req.getContextPath() + "/community/*");
	}else {
		req.getRequestDispatcher("/WEB-INF/views/common/error-page").forward(req, resp);
	}
	
	
	

	}
}
