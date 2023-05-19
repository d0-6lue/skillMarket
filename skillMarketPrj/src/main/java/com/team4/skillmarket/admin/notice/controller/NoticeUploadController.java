package com.team4.skillmarket.admin.notice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;


@MultipartConfig (
		maxFileSize = 1024 * 1024 * 50 ,
		maxRequestSize = 1024 * 1024 * 50 *10 
		)
@WebServlet("/admin/notice/upload")
public class NoticeUploadController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			List<String> changeNameList = new ArrayList<>();
			
			String path = req.getServletContext().getRealPath("/static/img/notice/"); // 저장 경로
			Collection<Part> fileList = req.getParts(); // 'f' 파라미터로 전송된 값 가져오기
			
			
			int i = 0;
			String changeName = "";
			for(Part f : fileList) {
				i++;
				
				if (!f.getName().equals("f")) {
					continue;
				}
				String notice = "notice"; // 파일 이름 규칙 ** 게시글 번호 추가예정
				String orginName = f.getSubmittedFileName(); //원본 이름
				String ext = orginName.substring(orginName.lastIndexOf(".")); // 파일 확장자 가져오기
				
				changeName = notice + i + ext; // 변경된 파일명 
				File target = new File(path + changeName); // 저장 경로 + 이름 ** file -> (java.io)
				FileOutputStream os = new FileOutputStream(target); //파일 출력 스트림
				
				InputStream is = f.getInputStream();
				
				byte[] buf = new byte[1024]; // 버퍼 배열
				int size = 0;
				
				while((size = is.read(buf)) != -1) {
					os.write(buf, 0 , size);
				}
				
				changeNameList.add(changeName);
			}
			
			Gson gson = new Gson();
			changeName = gson.toJson(changeNameList);
						
			resp.getWriter().write(changeName.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
