package com.team4.skillmarket.admin.banner.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50 ,
		maxRequestSize = 1024 * 1024 * 50 *10 
		)
@WebServlet("/admin/banner/upload")
public class BannerUploadController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
			
        	String path = req.getServletContext().getRealPath("/static/img/banner/"); // 저장 경로
        	String no = req.getParameter("no");
    		Part file = req.getPart("image"); // 'formData' 파라미터로 전송된 값 가져오기
    		
    		
            String changeName = "배너이미지" + no + ".png"; // 파일명 설정
            
            
            // 기존에 같은 이름의 파일이 존재하면 제거
	        File existingFile = new File(path, changeName);
	        if (existingFile.exists()) {
	        	System.out.println(existingFile+"제거");
	            existingFile.delete();
            }
            
            // 파일 저장
            
            OutputStream os = new FileOutputStream(new File(path, changeName));
            
            InputStream is = file.getInputStream();
            
            byte[] buf = new byte[1024]; // 버퍼 배열
    		int size = 0;
    		
    		while((size = is.read(buf)) != -1) {
    			os.write(buf, 0 , size);
    		}
    		
            os.close();
            
            // 응답 데이터 생성
    		changeName = "/static/img/banner/"+changeName;
            
            // 응답 데이터 전송
    		resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(changeName);
        	
		
    	
    }
}
