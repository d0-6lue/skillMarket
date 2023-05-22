package com.team4.skillmarket.expert.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.service.ExpertService;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.utill.file.FileUploader;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 100 ,
		maxRequestSize = 1024 * 1024 * 1000
	)
@WebServlet("/expert/register")
public class RegisterExpertController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			ExpertService es = new ExpertService();
			List<EstimateCategoryVo> categoryList = es.getSelectCategory();
			
			if(loginMember != null) {
				req.setAttribute("categoryList", categoryList);
				req.getRequestDispatcher("/WEB-INF/views/expert/register.jsp").forward(req, resp);
			}else {
				req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요.");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMsg", e);
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			String introduce = req.getParameter("expertIntroduction");
			String time = req.getParameter("expertTime");
			String field = req.getParameter("expertField");
			String during = req.getParameter("expertCareerDuring");
			String[] careerList = req.getParameterValues("expertCareer");
			String[] educationList = req.getParameterValues("expertEducation");
			Part profilePart = req.getPart("f");
			
			String path = req.getServletContext().getRealPath("/static/img/profile/");
			String changeName = null;

			if(profilePart.getSize() > 0) {
				changeName = FileUploader.saveFile(path, profilePart);
			}
			
			ExpertVo ev = new ExpertVo();
			MemberVo mv = new MemberVo();
			
			ev.setMemberNo(loginMember.getMemberNo());
			ev.setFreelancerInroduction(introduce);
			ev.setFreelancerContactTime(time);
			ev.setFieldOfExpertise(field);
			
			mv.setMemberNo(loginMember.getMemberNo());
			mv.setMemberProfilePhoto(changeName);
			
			
			ExpertService es = new ExpertService();
			MemberService ms = new MemberService();
			
			
			int result = es.register(ev, during, careerList, educationList, mv);
			ExpertVo evo = ms.searchExpertInfo(loginMember);
			MemberVo mvo = ms.getMyInfo(loginMember);
			
			if(result == 1) {
				req.getSession().setAttribute("alertMsg", "전문가 등록이 완료됐습니다.");
				req.getSession().setAttribute("loginMember", mvo);
				req.getSession().setAttribute("loginExpert", evo);
				resp.sendRedirect(req.getContextPath() + "/home");
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("전문가 등록 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "전문가 등록 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
			
		}
		
		
		
	}

}
