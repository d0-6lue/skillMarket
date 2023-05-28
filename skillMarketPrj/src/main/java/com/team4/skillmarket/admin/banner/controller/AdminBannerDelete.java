package com.team4.skillmarket.admin.banner.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.admin.banner.service.BannerService;

@MultipartConfig
@WebServlet("/admin/banner/delete")
public class AdminBannerDelete extends HttpServlet{
	
	private final BannerService bs = new BannerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String no = req.getParameter("no");

			int deletBanner =  bs.deletBanner(no);
			
			if (deletBanner == 0) {
				resp.setCharacterEncoding("UTF-8");
	            resp.getWriter().write("삭제실패");
			}
			
			String path = req.getServletContext().getRealPath("/static/img/banner/"); // 저장 경로
			String changeName = "배너이미지" + no + ".png";
			File existingFile = new File(path, changeName);
	        if (existingFile.exists()) {
	        	System.out.println(existingFile+"제거");
	            existingFile.delete();
            }
			
			resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("삭제성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
