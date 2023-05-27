package com.team4.skillmarket.header.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.header.service.HeaderService;

@WebServlet("/home/get-category")
public class GetEstimateCategoryController extends HttpServlet {

	private final HeaderService headerService = new HeaderService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new Gson();
		
		String type = req.getParameter("type");
		String aboveNo = req.getParameter("aboveNo");
		
		if(aboveNo != null ) {
			List<EstimateCategoryVo> estimateSubCatVoList = headerService.getEstimateCatListByAboveNo(type, aboveNo);
			
			String estimateSubCatVoListStr = gson.toJson(estimateSubCatVoList);
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(estimateSubCatVoListStr);
		}
		else {
			List<EstimateCategoryVo> estimateCatVoList = headerService.getEstimateCatList(type);
			
			String categoryCatVoListStr = gson.toJson(estimateCatVoList);
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(categoryCatVoListStr);
		}
		
		
	}
	
}
