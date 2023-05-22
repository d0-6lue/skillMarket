package com.team4.skillmarket.expert.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.service.ExpertService;
import com.team4.skillmarket.expert.vo.CareerVo;
import com.team4.skillmarket.expert.vo.EducationVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.service.MemberService;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.utill.file.FileUploader;

@WebServlet("/expert-info")
public class ExpertInfoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			ExpertVo loginExpert = (ExpertVo) req.getSession().getAttribute("loginExpert");
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			if(loginExpert == null) {
				req.getSession().setAttribute("alertMsg", "전문가 등록을 먼저 해주세요.");
				resp.sendRedirect(req.getContextPath() + "/expert/register");
				
				return;
			}
			
			ExpertService es = new ExpertService();
			MemberService ms = new MemberService();
			
			List<EstimateCategoryVo> categoryList = es.getSelectCategory();
			List<CareerVo> careerList = es.getCareer(loginExpert);
			List<EducationVo> educationList = es.getEducation(loginExpert);
			
			ExpertVo evo = ms.searchExpertInfo(loginMember);
			
			
			if(loginMember != null && evo != null) {
				req.getSession().setAttribute("loginExpert", evo);
				req.setAttribute("categoryList", categoryList);
				req.setAttribute("careerList", careerList);
				req.setAttribute("educationList", educationList);
				req.getRequestDispatcher("/WEB-INF/views/expert/expertinfo.jsp").forward(req, resp);
			}else {
				req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요.");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} catch (Exception e) {
			System.out.println("전문가 정보 불러오기 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "전문가 정보 페이지 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			ExpertVo loginExpert = (ExpertVo) req.getSession().getAttribute("loginExpert");
			
			String introduce = req.getParameter("expertIntroduction");
			String time = req.getParameter("expertTime");
			String field = req.getParameter("expertField");
			String during = req.getParameter("expertCareerDuring");
			String[] careerList = req.getParameterValues("expertCareer");
			String[] educationList = req.getParameterValues("expertEducation");
			
			ExpertVo ev = new ExpertVo();
			MemberVo mv = new MemberVo();
			
			ev.setMemberNo(loginMember.getMemberNo());
			ev.setFreelancerInroduction(introduce);
			ev.setFreelancerContactTime(time);
			ev.setFieldOfExpertise(field);
			
			ExpertService es = new ExpertService();
			MemberService ms = new MemberService();
			
			int result = es.updateExpertInfo(ev, during, loginExpert, careerList, educationList);
			ExpertVo evo = ms.searchExpertInfo(loginMember);
			MemberVo mvo = ms.getMyInfo(loginMember);
			
			if(result == 1) {
				req.getSession().setAttribute("alertMsg", "전문가 수정이 완료됐습니다.");
				req.getSession().setAttribute("loginMember", mvo);
				req.getSession().setAttribute("loginExpert", evo);
				resp.sendRedirect(req.getContextPath() + "/home");
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("전문가 정보 수정 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "전문가 정보 수정 중 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
			
		}
		
	}
	
} // class
