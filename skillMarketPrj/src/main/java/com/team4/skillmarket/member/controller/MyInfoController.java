package com.team4.skillmarket.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUpload;

import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.service.ExpertService;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.utill.file.FileUploader;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 100 ,
		maxRequestSize = 1024 * 1024 * 1000
	)
@WebServlet("/my-info")
public class MyInfoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			ExpertService es = new ExpertService();
			List<EstimateCategoryVo> categoryList = es.getSelectCategory();
			
			if(loginMember != null) {
				req.setAttribute("categoryList", categoryList);
				req.getRequestDispatcher("/WEB-INF/views/member/myinfo.jsp").forward(req, resp);
			}else {
				req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요.");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", e);
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}

		
	} // doget
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo mvo = (MemberVo) req.getSession().getAttribute("loginMember");
			
			String memberNick = req.getParameter("memberNick");
			String memberEmail = req.getParameter("memberEmail");
			String memberPhone = req.getParameter("memberPhone");
			String memberInterst = req.getParameter("memberFavorite");
			String memberBank = req.getParameter("memberBank");
			String memberAccount = req.getParameter("memberAccount");
			Part profilePart = req.getPart("f");
			
			String path = req.getServletContext().getRealPath("/static/img/profile/");
			String changeName = null;

			if(profilePart.getSize() > 0) {
				changeName = FileUploader.saveFile(path, profilePart);
			}
			
			MemberVo vo = new MemberVo();
			vo.setMemberNo(mvo.getMemberNo());
			vo.setMemberNick(memberNick);
			vo.setMemberEmail(memberEmail);
			vo.setMemberPhone(memberPhone);
			vo.setMemberInterst(memberInterst);
			vo.setMemberProfilePhoto(changeName);
			vo.setMemberBank(memberBank);
			vo.setMemberAccount(memberAccount);
			
			MemberService ms = new MemberService();
			int result = ms.changeInfo(vo);
			
			MemberVo loginMember = ms.getMyInfo(vo);
			
			if(result == 1) {
				req.getSession().setAttribute("alertMsg", "회원수정이 완료됐습니다.");
				req.getSession().setAttribute("loginMember", loginMember);
				resp.sendRedirect(req.getContextPath() + "/my-info");
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("회원정보수정 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "회원정보수정 중 에러 발생...");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
		
		
	}
	
}
