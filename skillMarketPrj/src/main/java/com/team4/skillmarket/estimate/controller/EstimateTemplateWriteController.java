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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team4.skillmarket.estimate.file.EstimateFileUploader;
import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateFaqVo;
import com.team4.skillmarket.estimate.vo.EstimateOptionVo;
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

            // 파일업로더 객체생성

            // 첨부파일들 다루기

            // 데꺼
            String freelancerNo = expertMember.getFreelancerNo();
            String jobTitle = multi.getParameter("job-title");
            String EstimateCatNo = multi.getParameter("cateCode");
            String jobPrice = multi.getParameter("job-price");
            String jobDuration = multi.getParameter("job-duration");
            String jobSummary = multi.getParameter("job-summary");
            String jobDescription = multi.getParameter("job-description");
            
            logger.info("freelancerNo: " + freelancerNo);
            logger.info("jobTitle: " + jobTitle);
            logger.info("EstimateCatNo: " + EstimateCatNo);
            logger.info("jobPrice: " + jobPrice);
            logger.info("jobDuration: " + jobDuration);
            logger.info("jobSummary: " + jobSummary);
            logger.info("jobDescription: " + jobDescription);

            // 데뭉
            EstimateVo estimateVo = new EstimateVo();
            estimateVo.setFreelancerNo(Integer.parseInt(freelancerNo));
            estimateVo.setEstimateCatNo(EstimateCatNo);
            estimateVo.setEstimateTitle(jobTitle);
            estimateVo.setEstimateDuration(jobDuration);
            estimateVo.setEstimateLineIntroduction(jobSummary);
            estimateVo.setEstimatePrice(jobPrice);
            estimateVo.setEstimateDetail(jobDescription);
            

            // 메인이미지 상세이미지 초기화
            AttachmentVo mainImage = null;
            List<AttachmentVo> detailImages = new ArrayList<>();

            // 첨부파일들 다루기
            Enumeration<String> fileNames = multi.getFileNames();
            while (fileNames.hasMoreElements()) {
                String fileName = fileNames.nextElement();
                String originalFileName = multi.getOriginalFileName(fileName);
                String serverFileName = multi.getFilesystemName(fileName);

                if (originalFileName == null || serverFileName == null) {
                    continue;
                }

                AttachmentVo attachment = new AttachmentVo();
                attachment.setAttachmentOriginName(originalFileName);
                attachment.setAttachmentServerName(serverFileName);

                // 파일 업로드 시 메인 이미지 여부 체크
                String isMainImage = multi.getParameter("is-main-image");
                if (fileName.equals("main-file-upload") && isMainImage != null && isMainImage.equals("true")) {
                    attachment.setMainImage(true);
                    mainImage = attachment;
                    logger.info("Main Image: " + originalFileName + " (" + serverFileName + ")");
                } else {
                    attachment.setMainImage(false);
                    detailImages.add(attachment);
                    logger.info("Detail Image: " + originalFileName + " (" + serverFileName + ")");
                }
            }

            estimateVo.setMainImage(mainImage);
            estimateVo.setDetailImages(detailImages);

            // 추가 옵션 정보 저장
            List<EstimateOptionVo> estimateOptions = Optional.ofNullable(multi.getParameterValues("optionName"))
                    .map(names -> {
                        List<EstimateOptionVo> options = new ArrayList<>();
                        for (String name : names) {
                            EstimateOptionVo option = new EstimateOptionVo();
                            option.setEstimateOptionName(name);
                            // 추가 처리 로직
                            options.add(option);
                        }
                        return options;
                    })
                    .orElse(Collections.emptyList());
            estimateVo.setAdditionalOptions(estimateOptions);

            // FAQ 정보 저장
            List<EstimateFaqVo> estimateFaqs = Optional.ofNullable(multi.getParameterValues("faqQuestion"))
                    .map(questions -> {
                        List<EstimateFaqVo> faqs = new ArrayList<>();
                        for (String question : questions) {
                            EstimateFaqVo faq = new EstimateFaqVo();
                            faq.setEstimateFaqQContent(question);
                            // 추가 처리 로직
                            faqs.add(faq);
                        }
                        return faqs;
                    })
                    .orElse(Collections.emptyList());
            estimateVo.setFaqs(estimateFaqs);


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

   

