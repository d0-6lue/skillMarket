package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.CommunityCommentVo;
import com.team4.skillmarket.member.vo.MemberVo;


//댓글 작성
@WebServlet("/community/comment/write")
public class CommunityCommentWriteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            HttpSession session = req.getSession();
            MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
            String writerNo = null;
            if (loginMember != null) {
                writerNo = loginMember.getMemberNo();
                System.out.println("writerNo: " + writerNo);
            }

            String boardNo = req.getParameter("no");
            String comment = req.getParameter("comment");

            CommunityCommentVo vo = new CommunityCommentVo();
            vo.setBoardNo(boardNo);
            vo.setCommentContent(comment);
            vo.setMemberNo(writerNo);

            CommunityService cs = new CommunityService();
            int result = cs.commentWrite(vo);

            PrintWriter out = resp.getWriter();
            if (result == 1) {
                out.write("ok");
            }

        } catch (Exception e) {
            System.out.println("글쓰기 에러");
            e.printStackTrace();

        }
    }

}
