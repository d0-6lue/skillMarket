package com.team4.skillmarket.estimate.controller;


import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            // 파일 데꺼
            MultipartRequest multi = new MultipartRequest(
                    req,
                    getServletContext().getRealPath(UPLOAD_PATH),
                    MAX_FILE_SIZE,
                    "UTF-8",
                    new DefaultFileRenamePolicy() {
                        @Override
                        public File rename(File file) {
                            // 파일 이름 변경 로직 구현
                            // UUID로 새로운 파일 이름 생성
                            String extension = getExtension(file.getName());
                            String newName = UUID.randomUUID().toString() + extension;
                            File renamedFile = new File(file.getParent(), newName);
                            return renamedFile;
                        }

                        private String getExtension(String fileName) {
                            int dotIndex = fileName.lastIndexOf('.');
                            if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
                                return fileName.substring(dotIndex);
                            }
                            return "";
                        }
                    }
            );

            // Create FileUploader
            EstimateFileUploader estimFileUploader = new EstimateFileUploader(UPLOAD_PATH, MAX_FILE_SIZE);

            // Handle attachments
            List<AttachmentVo> attachmentList = estimFileUploader.uploadAttachments(multi);

            // Extract estimate information and create object
            String jobTitle = multi.getParameter("job-title");
            String jobPrice = multi.getParameter("job-price");
            String jobDuration = multi.getParameter("job-duration");
            String jobSummary = multi.getParameter("job-summary");
            String jobDescription = multi.getParameter("job-description");
            String freelancerNo = expertMember.getFreelancerNo();
            String EstimateCatNo = multi.getParameter("cateCode");
            System.out.println(EstimateCatNo);

            // 데뭉
            EstimateVo estimate = new EstimateVo();
            estimate.setFreelancerNo(Integer.parseInt(freelancerNo));
            estimate.setEstimateCatNo(EstimateCatNo);
            estimate.setEstimateTitle(jobTitle);
            estimate.setEstimateDuration(jobDuration);
            estimate.setEstimateLineIntroduction(jobSummary);
            estimate.setEstimatePrice(jobPrice);
            estimate.setEstimateDetail(jobDescription);
            

            // Set the list of processed attachments
            estimate.setAttachments(attachmentList);

            // Update file paths in estimate object
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
            System.out.println("견적서 작성에러");
        }
    }
    
}

   

