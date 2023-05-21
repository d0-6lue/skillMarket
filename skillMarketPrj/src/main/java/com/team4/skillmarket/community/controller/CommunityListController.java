package com.team4.skillmarket.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.community.service.CommunityService;
import com.team4.skillmarket.community.vo.CommunityPostVo;

@WebServlet("/community/*")
public class CommunityListController extends HttpServlet {
    private final CommunityService cs = new CommunityService();
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String searchType = req.getParameter("searchType");
            String searchValue = req.getParameter("searchValue");

            int cnt = cs.getBoardListCnt(searchType, searchValue);
            String page_ = req.getParameter("page");
            if (page_ == null) {
                page_ = "1";
            }
            int page = Integer.parseInt(page_);
            int pageLimit = 5;
            int boardLimit = 20;

            PageVo pv = new PageVo(cnt, page, pageLimit, boardLimit);

            List<CommunityPostVo> volist = cs.getFreeBoardList(pv);

            req.setAttribute("volist", volist);
            req.setAttribute("pv", pv);

            req.getRequestDispatcher("/WEB-INF/views/community/board.jsp").forward(req, resp);

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to retrieve board list...");
            e.printStackTrace();

            req.setAttribute("errorMsg", "Failed to retrieve board list.");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
        }
    }

}
