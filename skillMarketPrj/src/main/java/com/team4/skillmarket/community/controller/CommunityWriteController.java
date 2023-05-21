package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.Part;
import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.FreeBoardCategoryVo;
import com.team4.skillmarket.community.vo.FreeBoardVo;
import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.utill.file.FileUploader;

@WebServlet("/community/post/write")
public class CommunityWriteController extends HttpServlet{
	
	private CommunityService cs;
	
	 @Override
    public void init() throws ServletException {
        cs = new CommunityService();
    }
	
	// 커뮤니티 글쓰기 화면 조회하기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 대충 로그인 검증 로직하기 ~

		HttpSession session = req.getSession();
		MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");

		if (loginMember == null) {
			req.setAttribute("errorMsg", "로그인 먼저 해주세요");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			return;
		}
		
		// 커뮤니티 주제 가져오기
		List<FreeBoardCategoryVo> cvolist = new ArrayList<>();
		try {
			cvolist = cs.getFreeBoardCategory();
		}catch (Exception e) {
			e.printStackTrace();
		}

		//카테고리 리스트 넘기기
		req.setAttribute("cvolist", cvolist);
		req.getRequestDispatcher("/WEB-INF/views/community/post/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        HttpSession session = req.getSession();
	        MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");

	        String title = req.getParameter("title");
	        String editordata = req.getParameter("editordata");
	        String categoryNo = req.getParameter("freeBoardCategoryNo");
	        String writerNo = loginMember.getMemberNo();

	        // Data validation
	        if (title == null || editordata == null || categoryNo == null || writerNo == null) {
	            throw new IllegalArgumentException("Invalid data.");
	        }

	        // Get image paths from input form
	        String imagePaths = req.getParameter("imagePaths");

	        FreeBoardVo bvo = new FreeBoardVo();
	        bvo.setFreeBoardTitle(title);
	        bvo.setFreeBoardContent(editordata);
	        bvo.setFreeBoardCategoryNo(categoryNo);
	        bvo.setMemberNo(writerNo);
	        bvo.setFreeBoardAttachment(imagePaths);

	        int result = cs.write(bvo);

	        if (result == 1) {
	            resp.sendRedirect(req.getContextPath() + "/community/all");
	        } else {
	            throw new IllegalStateException("Failed to write the post.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("errorMsg", e.getMessage());
	        req.getRequestDispatcher("/WEB-INF/views/comm/error-page.jsp").forward(req, resp);
	    }
	}




}
