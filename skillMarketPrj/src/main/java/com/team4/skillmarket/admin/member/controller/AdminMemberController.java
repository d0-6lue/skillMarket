package com.team4.skillmarket.admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.member.service.memberListService;
import com.team4.skillmarket.admin.member.vo.memberListVo;

@WebServlet("/admin/members")
public class AdminMemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 어드민 로그인 정보 없으면 로그인 창으로
		if (req.getSession().getAttribute("AdminLoginVo") == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/login");
			return;
		}
		
		try {
			
			
			memberListService ms = new memberListService();
			List<memberListVo> memberArrList =  ms.selectMemberList();
			
			// 정지, 탈퇴 유무 체크
			int statusCnt = 0;
			for(memberListVo e: memberArrList) {
				if (e.getStatusNo().equals("3") || e.getStatusNo().equals("4")) {
					statusCnt ++;
				}
			}
			
			req.setAttribute("statusCnt", statusCnt);
			req.setAttribute("memberArrList", memberArrList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/members.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
