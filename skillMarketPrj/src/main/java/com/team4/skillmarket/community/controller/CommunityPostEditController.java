package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.FreeBoardCategoryVo;
import com.team4.skillmarket.community.vo.FreeBoardVo;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/community/post/edit")
public class CommunityPostEditController extends HttpServlet{
	
	private CommunityService cs = null;
	
	private FreeBoardVo vo = null;
	
	 @Override
    public void init() throws ServletException {
        cs = new CommunityService();
    }
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {	
			//데꺼
			String no = req.getParameter("no");
			// 커뮤니티 주제 가져오기
			List<FreeBoardCategoryVo> cvolist = new ArrayList<>();	
			
			//자유게시판 정보가져오기
			vo = cs.getFreeBoardByNo(no);
			
			//카테고리 가져오기
			cvolist = cs.getFreeBoardCategory();
			
			//화면
			req.setAttribute("cvolist", cvolist);
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/community/post/edit.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("[ERROR] notice edit errr...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "수정하기 화면 조회 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        HttpSession session = req.getSession();
	        MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");

	        if (loginMember == null) {
	            req.setAttribute("errorMsg", "로그인먼저해주세요");
	            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
	            return;
	        }

	        // Get data
	        String no = req.getParameter("no");
	        String title = req.getParameter("title");
	        String editordata = req.getParameter("editordata");
	        String categoryNo = req.getParameter("freeBoardCategoryNo");
	        String writerNo = loginMember.getMemberNo();

	        System.out.println("no: " + no);
	        System.out.println("title: " + title);
	        System.out.println("editordata: " + editordata);
	        System.out.println("categoryNo: " + categoryNo);
	        System.out.println("writerNo: " + writerNo);

	        FreeBoardVo vo = new FreeBoardVo(); 
	        vo.setBoardNo(no);
	        vo.setFreeBoardTitle(title);
	        vo.setFreeBoardContent(editordata);
	        vo.setFreeBoardCategoryNo(categoryNo);
	        vo.setMemberNo(writerNo);

	        System.out.println("vo: " + vo);

	        int result = 0;
	        try {
	            result = cs.FreeBoardedit(vo);
	            System.out.println("Result: " + result);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        if (result == 1) {
	            session.setAttribute("alertMsg", "수정 완료됨");
	            resp.sendRedirect(req.getContextPath() + "/community/post/detail?bno=" + no);
	        } else {
	            throw new IllegalStateException("Failed to create post");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("errorMsg", e.getMessage());
	        req.getRequestDispatcher("/WEB-INF/views/comm/error-page.jsp").forward(req, resp);
	    }
	}





}
