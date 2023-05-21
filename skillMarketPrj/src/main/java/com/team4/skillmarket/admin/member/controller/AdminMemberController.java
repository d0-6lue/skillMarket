package com.team4.skillmarket.admin.member.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.member.service.memberListService;
import com.team4.skillmarket.admin.member.vo.AdminMemberVo;
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
			int newBeCnt = 0;
			int freeLancerCnt = 0;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss.SSSSSSSSS");
			LocalDate today = LocalDate.now();

			for (memberListVo e : memberArrList) {
			    if (e.getStatusNo().equals("3") || e.getStatusNo().equals("4")) {
			        statusCnt++;
			    }
			    Timestamp memberSignTimestamp = e.getMemberSignDate();
			    LocalDateTime memberSignDateTime = memberSignTimestamp.toLocalDateTime();
			    LocalDate memberSignDate = memberSignDateTime.toLocalDate();

			    if (today.minusDays(3).isBefore(memberSignDate)) {
			        newBeCnt++;
			    }
			    if (e.getFreelancerY().equals("Y")) {
			    	freeLancerCnt++;
				}
			}

			AdminMemberVo adminMemberVo = new AdminMemberVo();
			adminMemberVo.setStatusCnt(statusCnt);
			adminMemberVo.setNewBeCnt(newBeCnt);
			adminMemberVo.setFreeLancerCnt(freeLancerCnt);
			
			req.setAttribute("adminMemberVo", adminMemberVo);
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
