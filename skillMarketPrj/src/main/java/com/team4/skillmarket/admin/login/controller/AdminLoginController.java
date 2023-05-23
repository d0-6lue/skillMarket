package com.team4.skillmarket.admin.login.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.team4.skillmarket.admin.login.service.AdminLoginService;
import com.team4.skillmarket.admin.login.vo.AdminLoginVo;
import com.team4.skillmarket.admin.login.vo.AdminVo;


@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/home/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		
			// 클라이언트로부터 전송된 JSON 데이터를 읽어옴
	        BufferedReader reader = req.getReader();
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
	        String json = sb.toString();

	        // JSON 데이터를 AdminLoginVo 객체로 변환
	        Gson gson = new Gson();
	        AdminLoginVo avo = gson.fromJson(json, AdminLoginVo.class);
				        
			AdminLoginService as = new AdminLoginService();
			AdminVo adminVo = as.login(avo);
			
			JsonObject errorObject = new JsonObject();
			if (adminVo != null) {
				req.getSession().setAttribute("AdminLoginVo", adminVo);
				
				// 로그인 성공 시
				errorObject.addProperty("success", true);
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(new Gson().toJson(errorObject));
				
				
			} else {
				// 로그인 실패 시
				errorObject.addProperty("success", false);
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(new Gson().toJson(errorObject));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
