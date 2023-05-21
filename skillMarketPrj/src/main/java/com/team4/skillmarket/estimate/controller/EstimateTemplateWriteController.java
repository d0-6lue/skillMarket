package com.team4.skillmarket.estimate.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team4.skillmarket.estimate.file.EstimateFileUploader;
import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.utill.file.AttachmentVo;

//견적서 템플릿 작성하기 컨트롤러 
@WebServlet("/myestimate")
public class EstimateTemplateWriteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_PATH = "/upload/estimate";
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 50;

    private EstimateService estimateService;

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
        
        //카테고리 리스트받아왔어요
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
            // 파일 데꺼
            MultipartRequest multi = new MultipartRequest(
                    req,
                    getServletContext().getRealPath(UPLOAD_PATH),
                    MAX_FILE_SIZE,
                    "UTF-8",
                    new DefaultFileRenamePolicy()
            );

            // 파일업로더만들기
            EstimateFileUploader fileUploader = new EstimateFileUploader(UPLOAD_PATH, MAX_FILE_SIZE);

            // 파일업로더 ~
            List<AttachmentVo> attachmentList = fileUploader.uploadAttachments(multi);

            // 데꺼
            String jobTitle = multi.getParameter("job-title");
            String jobPrice = multi.getParameter("job-price");
            String jobDuration = multi.getParameter("job-duration");
            String jobSummary = multi.getParameter("job-summary");
            String jobDescription = multi.getParameter("job-description");
            String freelancerNo = expertMember.getFreelancerNo();

            // 데뭉
            EstimateVo estimate = new EstimateVo();
            estimate.setFreelancerNo(Integer.parseInt(freelancerNo));
            estimate.setEstimateCatNo(2);
            estimate.setEstimateTitle(jobTitle);
            estimate.setEstimateDuration(jobDuration);
            estimate.setEstimateLineIntroduction(jobSummary);
            estimate.setEstimatePrice(jobPrice);
            estimate.setEstimateDetail(jobDescription);

            // 파일 데뭉
            estimate.setAttachments(attachmentList);

            // 파일 경로 담기 
            List<String> attachmentPaths = new ArrayList<>();
            for (AttachmentVo attachment : attachmentList) {
                String attachmentPath = UPLOAD_PATH + "/" + attachment.getAttachmentServerName();
                attachmentPaths.add(attachmentPath);
            }
            estimate.setAttachmentPaths(attachmentPaths);

            // 견적서작성하기
            int result = estimateService.writeEstimate(estimate);

            if (result > 0) {
                resp.sendRedirect(req.getContextPath() + "/esti");
            } else {
                throw new IllegalStateException("Failed to write the estimate...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "Failed to write the estimate...");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
        }
    }
    
}

   

