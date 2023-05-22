package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.CommunityCommentVo;
import com.team4.skillmarket.member.vo.MemberVo;

@WebServlet("/community/comment/list")
public class CommunityCommentWriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String no = req.getParameter("no");
            
            CommunityService cs = new CommunityService();
            List<CommunityCommentVo> list = cs.getCommentList(no);
          
            Gson gson = new Gson();
            String jsonStr = gson.toJson(list);

            //문자열 내보내기
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.write(jsonStr);


        } catch (Exception e) {
            System.out.println("글쓰기 에러");
            e.printStackTrace();

        }
    }

}
