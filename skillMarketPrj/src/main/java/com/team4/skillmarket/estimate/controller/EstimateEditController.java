package com.team4.skillmarket.estimate.controller;




import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;


//견적서 작성중 수정하는 컨트롤러 
@WebServlet("/myestimate/edit/*")
public class EstimateEditController extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(EstimateEditController.class.getName());

	private static final long serialVersionUID = 1L;
    private EstimateService estimateService;

    public void init() throws ServletException {
        estimateService = new EstimateService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
        ExpertVo expertMember = (ExpertVo) session.getAttribute("loginExpert");
        if (loginMember == null || expertMember == null) {
            req.setAttribute("errorMsg", "접근 권한 없음");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
            return;
        }

        try {
            // 데이터
            String no = req.getParameter("no");

            if (no != null) {
                // 서비스
                EstimateVo estivo = estimateService.getEstimate(Integer.parseInt(no));
                LOGGER.info(no);

                // 화면
                req.setAttribute("estivo", estivo);
                LOGGER.info("estivo");
                req.getRequestDispatcher("/WEB-INF/views/estimate/estimateEdit.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorMsg", "견적서 번호가 필요합니다.");
                req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("errorMsg", "견적서 번호가 올바르지 않습니다.");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("[ERROR] notice edit errr...");
            e.printStackTrace();

            req.setAttribute("errorMsg", "수정하기 화면 조회 실패 ...");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    }

    
}

   

