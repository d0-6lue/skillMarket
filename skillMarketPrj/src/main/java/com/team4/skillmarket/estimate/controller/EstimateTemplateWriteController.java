package com.team4.skillmarket.estimate.controller;


import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateFaqVo;
import com.team4.skillmarket.estimate.vo.EstimateOptionVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;

//견적서 템플릿 작성하기 컨트롤러 
@WebServlet("/myestimate")
public class EstimateTemplateWriteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EstimateService estimateService;
    
    private static final Logger logger = Logger.getLogger(EstimateTemplateWriteController.class.getName());


    @Override
    public void init() throws ServletException {
        estimateService = new EstimateService();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
        
      

        if (loginMember == null) {
            req.setAttribute("errorMsg", "로그인을 먼저 해주세요");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
            return;
        }
        
        List<EstimateCategoryVo> esticatevoList = new ArrayList<>();
        try {
        	 esticatevoList = estimateService.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // Gson을 사용하여 카테고리 리스트를 JSON 형식으로 변환
        Gson gson = new Gson();
        String json = gson.toJson(esticatevoList);
        
        req.setAttribute("estiCatevoList", json);
        

        req.getRequestDispatcher("/WEB-INF/views/estimate/estimatetemplatewrite.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ExpertVo expertMember = (ExpertVo) session.getAttribute("loginExpert");

        try {
        	// 데꺼(그냥 텍스트에 이미지 담는걸로 바꿈) 개편함
        	String freelancerNo = expertMember.getFreelancerNo();
            String jobTitle = req.getParameter("job-title");
            String EstimateCatNo = req.getParameter("cateCode");
            String jobPrice = req.getParameter("job-price");
            String jobDuration = req.getParameter("job-duration");
            String jobSummary = req.getParameter("job-summary");
            String jobDescription = req.getParameter("job-description");
            String mainimage = req.getParameter("main-image");
            String subImage = req.getParameter("sub-image");
            
    
            // 데뭉
            EstimateVo estimateVo = new EstimateVo();
            estimateVo.setFreelancerNo(freelancerNo);
            estimateVo.setEstimateCatNo(EstimateCatNo);
            estimateVo.setEstimateTitle(jobTitle);
            estimateVo.setEstimateDuration(jobDuration);
            estimateVo.setEstimateLineIntroduction(jobSummary);
            estimateVo.setEstimatePrice(jobPrice);
            estimateVo.setEstimateDetail(jobDescription);
            estimateVo.setMainImage(mainimage);
            estimateVo.setSubImage(subImage);


            String[] optionTitles = req.getParameterValues("additionalOptions[].title");
            String[] optionPrices = req.getParameterValues("additionalOptions[].price");
            String[] optionWorks = req.getParameterValues("additionalOptions[].work");

            List<EstimateOptionVo> estimateOptions = new ArrayList<>();

            if (optionTitles != null && optionPrices != null && optionWorks != null) {
                for (int i = 0; i < optionTitles.length; i++) {
                    String title = optionTitles[i];
                    String price = optionPrices[i];
                    String work = optionWorks[i];

                    EstimateOptionVo estimateOption = new EstimateOptionVo();
                    estimateOption.setEstimateOptionName(title);
                    estimateOption.setEstimateOptionPrice(price);
                    estimateOption.setEstimateOptionQuantity(work);

                    estimateOptions.add(estimateOption);
                }
            }

            estimateVo.setAdditionalOptions(estimateOptions);


            	// 파라미터 값 로그 출력
            	if (!estimateOptions.isEmpty()) {
            	    for (EstimateOptionVo option : estimateOptions) {
            	        System.out.println("Title: " + option.getEstimateOptionName());
            	        System.out.println("Price: " + option.getEstimateOptionPrice());
            	        System.out.println("Work: " + option.getEstimateOptionQuantity());
            	        System.out.println("--------------------");
            	    }
            	}




            // FAQ 정보 저장
        	String[] questions = req.getParameterValues("custom-question");
        	String[] answers = req.getParameterValues("custom-answer");
        	List<EstimateFaqVo> estimateFaqs = null;
        	if ( questions != null && answers != null) {
        	    estimateFaqs = new ArrayList<>();
        	    for (int i = 0; i < questions.length; i++) {
        	        String question = questions[i];
        	        String answer = answers[i];

        	        EstimateFaqVo faq = new EstimateFaqVo();
        	        faq.setEstimateFaqQContent(question);
        	        faq.setEstimateFaqAContent(answer);

        	        estimateFaqs.add(faq);
        	    }
        	    estimateVo.setFaqs(estimateFaqs);
        	}
        	
        	



            // 견적서작성하기
            int result = estimateService.writeEstimate(estimateVo, estimateOptions, estimateFaqs);

            if (result > 0) {
                resp.sendRedirect(req.getContextPath() + "/esti");
            } else {
                throw new IllegalStateException("Failed to write the estimate...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("견적서 작성에러");
        }
    }
    
}

   

