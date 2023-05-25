package com.team4.skillmarket.estimate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.estimate.service.EstimateService;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;

@WebServlet("/category/*")
public class EstimatePreviewController extends HttpServlet {
	
	private EstimateService estimateService;
    
    @Override
    public void init() throws ServletException {
        estimateService = new EstimateService();
    }
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			String categoryNo = req.getParameter("catecode");
			
			// 카테고리 받아오기
			List<EstimateCategoryVo> esticatevoList = new ArrayList<>();
			try {
				esticatevoList = estimateService.getCategoryList();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			EstimateService es = new EstimateService();
			int cnt = es.getEstimateListCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			int pageLimit = 5;
			int boardLimit = 16;
			PageVo pv = new PageVo(cnt, page, pageLimit, boardLimit);
			
			
			Gson gson = new Gson();
			String json = gson.toJson(esticatevoList);
			
			//서비스
			List<EstimateVo> estimateList = estimateService.getEstimateList(pv,categoryNo);
			
			
			Map<String,String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			//화면
			req.setAttribute("estiCatevoList", json);
//			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("estimateList", estimateList);
			req.getRequestDispatcher("/WEB-INF/views/estimate/estimateList.jsp").forward(req, resp);
		}catch(Exception e) {
			System.out.println("[ERROR] 견적서 목록 조회 에러 ...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg" , "견적서 조회 실패 ..");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	

}
