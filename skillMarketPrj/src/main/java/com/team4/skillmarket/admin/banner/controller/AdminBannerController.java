package com.team4.skillmarket.admin.banner.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;

import com.team4.skillmarket.admin.banner.service.BannerService;
import com.team4.skillmarket.admin.banner.vo.BannerVo;

@MultipartConfig
@WebServlet("/admin/banner")
public class AdminBannerController extends HttpServlet{
	
	private final BannerService bs = new BannerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			List<BannerVo> bannerList = bs.getBannerList();
			
			if (bannerList == null) {
				throw new IllegalStateException("배너목록이 null");			
			}
			
			for(BannerVo f : bannerList) {
				System.out.println(f);
			}
			
			req.setAttribute("bannerList", bannerList);
			req.getRequestDispatcher("/WEB-INF/views/admin/home/banner.jsp").forward(req, resp);

			
		} catch (Exception e) {
			
			e.printStackTrace();

		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String bannerNo = req.getParameter("no");
			String bannerBackgroundcolor = req.getParameter("backgroundColor");
			String bannerFile = req.getParameter("file");
			
			System.out.println(bannerNo + ","+bannerBackgroundcolor+ ","+ bannerFile);
			
			BannerVo vo = new BannerVo();
			vo.setBannerNo(bannerNo);
			vo.setBannerBackgroundcolor(bannerBackgroundcolor);
			vo.setBannerFile(bannerFile);
			
			int updateBanner = bs.updateBanner(vo);
			
			if (updateBanner != 1) {
				resp.setCharacterEncoding("UTF-8");
	            resp.getWriter().write("error");
				throw new IllegalStateException("배너 등록 대실패");
			}
			
			// 응답 데이터 전송
    		resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("ok");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
