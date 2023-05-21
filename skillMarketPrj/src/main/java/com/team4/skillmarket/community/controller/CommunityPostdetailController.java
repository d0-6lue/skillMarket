package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.FreeBoardVo;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/community/post/detail")
public class CommunityPostdetailController extends HttpServlet{
	
	private final CommunityService cs = new CommunityService();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			HttpSession session = req.getSession();
	        MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			// 자유게시판 번호 데꺼
			String bno = req.getParameter("bno");
			
			FreeBoardVo vo =  cs.getFreeBoardByNo(bno);
			
			if(vo != null) {
				req.setAttribute("loginMember", loginMember);
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/community/post/detail.jsp").forward(req, resp);				
			}else {
				throw new Exception();
			}
			
			
		}catch(Exception e) {
			System.out.println("[ERROR] 커뮤니티 디테일 에러 ....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "상세조회 실패");
			req.getRequestDispatcher("~").forward(req, resp);
		}
	
	}

}
