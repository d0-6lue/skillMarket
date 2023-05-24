package com.team4.skillmarket.home.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.home.service.HomeService;


@WebServlet("/home")
public class HomeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HomeService hs = new HomeService();
			List<EstimateCategoryVo> cList = hs.getCategory();
			List<EstimateCategoryVo> pList = hs.getPopularCategoryList();

			if(cList.size() > 0) {
				req.setAttribute("pList", pList);
				req.setAttribute("bigCategoryList", cList);
				req.getRequestDispatcher("/WEB-INF/views/home/home.jsp").forward(req, resp);
				
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("카테고리 조회 중 에러 발생...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "카테고리 조회 중 에러 발생...");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, resp);
		}
	
	}
	
}
