package com.team4.skillmarket.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 50, // 50 MB
    maxRequestSize = 1024 * 1024 * 50 * 10 // 500 MB (10 files)
)
@WebServlet("/upload/community")
public class CommunityUploadController extends HttpServlet {
    // Upload directory
    private static final String UPLOAD_DIR = "/upload/community";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uploadFilePath = getServletContext().getRealPath(UPLOAD_DIR);

            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }

            Part part = request.getPart("file");
            String originalFileName = part.getSubmittedFileName();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String fileName = UUID.randomUUID().toString() + fileExtension;

            String filePath = uploadFilePath + File.separator + fileName;
            part.write(filePath);

            // Return the file path to the client
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            JSONObject json = new JSONObject();
            json.put("filePath", request.getContextPath() + UPLOAD_DIR + "/" + fileName); // Use 'request.getContextPath()' for the root path

            response.getWriter().write(json.toJSONString());
        } catch (Exception e) {
            throw new ServletException("File Upload Error", e);
        }
    }

}
